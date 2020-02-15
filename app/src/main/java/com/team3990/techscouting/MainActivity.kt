package com.team3990.techscouting

import android.bluetooth.*
import android.bluetooth.BluetoothGattService.SERVICE_TYPE_PRIMARY
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.os.ParcelUuid
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    /** Properties */

    private lateinit var server: BluetoothGattServer
    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var serverCallback: BluetoothGattServerCallback = object : BluetoothGattServerCallback() {
        override fun onConnectionStateChange(device: BluetoothDevice?, status: Int, newState: Int) {
            super.onConnectionStateChange(device, status, newState);

            // Display every connection and disconnection on the console.
            if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.i("Main Activity", "A device has been disconnected to the GATT server.")
            } else {
                Log.i("Main Activity", "A device has been connected to the GATT server.")
            }
        }

        override fun onServiceAdded(status: Int, service: BluetoothGattService?) {
            super.onServiceAdded(status, service)
            Log.i("Main Activity", "A service was added to the server.")
            startAdvertising()
        }

        override fun onCharacteristicReadRequest(device: BluetoothDevice?, requestId: Int, offset: Int, characteristic: BluetoothGattCharacteristic?) {
            super.onCharacteristicReadRequest(device, requestId, offset, characteristic)

            // Display every attempt to read the characteristics of a given service
            // hosted by the GATT server on the console.
            Log.i("Main Activity", "A device attempted to read a characteristic.")

            // Send a response to the client.
            server.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, "Hello, Tech Scout Desktop!".toByteArray());
        }
    }

    /** Activity's lifecycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the bluetooth manager property.
        bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

        // Get an instance of the Nav Controller..
        val navController = findNavController(R.id.navHostFragment)
        val topLevelDestinations = setOf(R.id.matchDataInputFragment, R.id.pitDataInputFragment, R.id.dataSheetListFragment, R.id.sharedDataSheetListFragment)

        // Initialize the app bar configuration property.
        appBarConfiguration = AppBarConfiguration(topLevelDestinations)

        // Setup the Bottom Navigation View and the Action Bar.
        setupBottomNavMenu(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onStart() {
        super.onStart()

        // Start the application's GATT Server.
        startBluetoothServer()
    }

    /** Methods */

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun startBluetoothServer() {
        server = bluetoothManager.openGattServer(this, serverCallback)

        // Get the appropriate BluetoothGattService instance.
        val serviceUUID = UUID.fromString("2c1926aa-086d-4013-9368-050bf6c215d9")
        val service = BluetoothGattService(serviceUUID, SERVICE_TYPE_PRIMARY)

        // Configure the service's properties.
        val characteristic = BluetoothGattCharacteristic(
            UUID.fromString("1591180d-2737-4bac-8fe9-b93655fbcd4e"),
            BluetoothGattCharacteristic.PROPERTY_READ or BluetoothGattCharacteristic.PROPERTY_WRITE,
            BluetoothGattCharacteristic.PERMISSION_READ or BluetoothGattCharacteristic.PERMISSION_WRITE
        )

        service.addCharacteristic(characteristic)

        // Create a request to add the created service.
        val requestInitiated = server.addService(service)

        // Check of the request to add the service has been initiated.
        if (requestInitiated) {
            Log.i("Main Activity", "The request to add the user_data service has been initiated.")
        }
    }

    private fun startAdvertising() {
        val bluetoothLeAdvertiser = bluetoothManager.adapter.bluetoothLeAdvertiser
        val pUUID = ParcelUuid(UUID.fromString("2c1926aa-086d-4013-9368-050bf6c215d9"))

        bluetoothLeAdvertiser?.let {
            val settings = AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                .setConnectable(true)
                .build()

            val data = AdvertiseData.Builder()
                .addServiceUuid(pUUID)
                .addServiceData(pUUID, "Data".toByteArray())
                .build()

            it.startAdvertising(settings, data, object: AdvertiseCallback() {
                override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
                    super.onStartSuccess(settingsInEffect)
                }

                override fun onStartFailure(errorCode: Int) {
                    Log.e( "BLE", "Advertising onStartFailure: $errorCode");
                    super.onStartFailure(errorCode)
                }
            })
        } ?: Log.w("Main Activity", "Failed to create advertiser")
    }

}
