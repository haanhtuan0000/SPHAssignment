package com.haanhtuan.sphassignment

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.haanhtuan.sphassignment.data.helper.NetworkHelper
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class NetworkTest {
    private var context: Context? = null

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
        assertNotNull(context)
    }

    @Test
    fun checkInternetConnection(){
        assertTrue(NetworkHelper.hasNetworkConnected(context!!))
    }
}