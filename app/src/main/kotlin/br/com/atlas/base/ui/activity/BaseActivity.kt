package br.com.atlas.base.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import br.com.atlas.base.ui.fragment.BaseFragment
import br.com.atlas.base.ui.data.ActivityData
import br.com.atlas.extension.cast
import br.com.atlas.extension.checkItemsAre
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
abstract class BaseActivity : AppCompatActivity() {

    object Values {
        const val BOOLEAN = "boolean"
        const val PARCELABLE = "parcelable"
        const val SERIALIZABLE = "serializable"
        const val PARCELABLE_LIST = "parcelable_list"

        const val ANIMATION_SIZE = 4
        const val ANIMATION_OTHER_SIZE = 2
    }

    object Store {
        const val MARKET = "market://details?id="
        const val PLAY = "https://play.google.com/store/apps/details?id="
    }

    companion object {

        fun showStore(activity: Activity?, packageName: String? = null) {
            var appPackageName = packageName
            if (appPackageName == null) appPackageName = activity?.packageName

            try {
                activity?.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("${Store.MARKET}$appPackageName")))
            } catch (e: ActivityNotFoundException) {
                activity?.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("${Store.PLAY}$appPackageName")))
            }
        }
    }

    abstract fun getLayout(): Int

    @Suppress("DEPRECATION")
    protected fun <F : Fragment> addFragment(fragment: F,
                                             container: Int,
                                             backStack: Boolean = false,
                                             animations: Array<Int>? = null) {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (fragmentManager.backStackEntryCount != 0) {
            replaceFragment(fragment, container, backStack, animations)
            return
        }

        if (animations != null) {
            when (animations.size) {
                Values.ANIMATION_OTHER_SIZE -> {
                    fragmentTransaction.setCustomAnimations(animations[0], animations[1])
                }
                Values.ANIMATION_SIZE -> {
                    fragmentTransaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
                }
            }
        }

        fragmentTransaction.add(container, fragment)
        checkBackStack(fragmentTransaction, fragment, backStack)
        fragmentTransaction.commit()
    }

    private fun <F : Fragment> checkBackStack(fragmentTransaction: FragmentTransaction,
                                              fragment: F, backStack: Boolean) {
        if (!backStack) {
            return
        }

        when (fragment) {
            is BaseFragment -> fragmentTransaction.addToBackStack(fragment.getName())
            else -> fragmentTransaction.addToBackStack(fragment.javaClass.canonicalName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    protected fun <F : Fragment> replaceFragment(fragment: F,
                                                 container: Int,
                                                 backStack: Boolean = false,
                                                 animations: Array<Int>? = null) {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (animations != null) {
            when (animations.size) {
                Values.ANIMATION_OTHER_SIZE -> {
                    fragmentTransaction.setCustomAnimations(animations[0], animations[1])
                }
                Values.ANIMATION_SIZE -> {
                    fragmentTransaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
                }
            }
        }

        fragmentTransaction.replace(container, fragment)
        checkBackStack(fragmentTransaction, fragment, backStack)
        fragmentTransaction.commit()
    }

    protected fun getFragment(idContainer: Int): Fragment? {
        return supportFragmentManager.findFragmentById(idContainer)
    }

    protected fun <F : Fragment> removeFragment(fragment: F?,
                                                animations: Array<Int>? = null) {
        if (fragment == null) {
            return
        }

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (animations != null && animations.size == Values.ANIMATION_SIZE) {
            fragmentTransaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
        }

        fragmentTransaction.remove(fragment).commitAllowingStateLoss()
    }

    protected fun <A : AppCompatActivity> clearBackStack(activity: A? = null) {
        val fragmentManager = activity?.supportFragmentManager ?: supportFragmentManager
        for (i in 0..fragmentManager.backStackEntryCount) fragmentManager.popBackStack()
    }

    fun <A : AppCompatActivity> startActivity(classActivity: Class<A>,
                                              flags: Int? = null,
                                              bundle: Bundle? = null,
                                              vararg data: Any?) {
        val intent: Intent = when (data.isEmpty()) {
            true -> Intent(this, classActivity)
            false -> {
                val list: List<Any?>? = data.toList()
                getIntentByData(classActivity, list)
            }
        }

        if (flags != null) {
            intent.flags = flags
        }

        startActivity(intent, bundle)
    }

    fun showStore(packageName: String? = null) {
        showStore(this, packageName)
    }

    private fun <A : AppCompatActivity> getIntentByData(classActivity: Class<A>,
                                                        data: List<Any?>?): Intent {
        val intent = Intent(this, classActivity)
        data?.forEach {
            when (it) {
                is ActivityData -> {
                    fillIntentByData(intent, it.data, it.key)
                }
                else -> {
                    fillIntentByData(intent, it)
                }
            }
        }

        return intent
    }

    private fun fillIntentByData(intent: Intent, data: Any?, key: String? = null) {
        when (data) {
            is Boolean -> intent.putExtra(key ?: Values.BOOLEAN, data)
            is Serializable -> intent.putExtra(key ?: Values.SERIALIZABLE, data)
            is ArrayList<*> -> intent.putParcelableArrayListExtra(key ?: Values.PARCELABLE_LIST,
                    data.checkItemsAre())
            is Parcelable -> intent.putExtra(key ?: Values.PARCELABLE, data)
            is Bundle -> intent.putExtras(data)
        }
    }

    @SuppressLint("RestrictedApi")
    fun <A : AppCompatActivity> startActivityForResult(classActivity: Class<A>,
                                                       requestCode: Int,
                                                       bundle: Bundle? = null,
                                                       vararg data: Any?) {
        val intent: Intent = when (data.isEmpty()) {
            true -> Intent(this, classActivity)
            false -> {
                val list: List<Any?>? = data.toList()
                getIntentByData(classActivity, list)
            }
        }

        startActivityForResult(intent, requestCode, bundle)
    }

    protected fun <S : Serializable> getSerializable(key: String? = null): S? {
        val serializable: Serializable? = intent.getSerializableExtra(key ?: Values.SERIALIZABLE)
        return serializable?.cast()
    }

    protected fun <P : Parcelable> getParcelable(key: String? = null): P {
        return intent.getParcelableExtra(key ?: Values.PARCELABLE)
    }

    protected fun <P : Parcelable> getParcelableList(key: String? = null): ArrayList<P>? {
        return intent.getParcelableArrayListExtra<P>(key ?: Values.PARCELABLE_LIST)
    }

    protected fun getBoolean(key: String? = null): Boolean {
        return intent.getBooleanExtra(key ?: Values.BOOLEAN, false)
    }

    fun showToast(message: String?, view: View? = null, textViewId: Int? = null) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        if (view != null){
            toast.view = view
        }

        centralize(toast, textViewId)
        toast.show()
    }

    private fun centralize(toast: Toast, textViewId: Int? = null) {
        var defaultId: Int = android.R.id.message
        if (textViewId != null) {
            defaultId = textViewId
        }

        val textView:TextView = toast.view.findViewById(defaultId)
        textView.gravity = Gravity.CENTER
    }

    fun showSnack(view: View?, message: String, color: Int? = null) {
        if (view == null) {
            return
        }

        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        if (color != null) {
            snackBar.view.setBackgroundColor(ContextCompat.getColor(this, color))
        }

        snackBar.show()
    }
}
