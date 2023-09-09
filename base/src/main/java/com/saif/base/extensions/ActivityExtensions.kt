package com.saif.base.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData

/*Live data extension function which'll set initial value*/
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun AppCompatActivity.replaceFragment(
    fragment: Fragment, frameId: Int,
    addBackStack: Boolean = false,
    clearBackStack: Boolean = false,
    allowMultipleInstances: Boolean = false,
    popBackStackInclusive: String? = null
) {
    //        double tapping to open fragment twice wont work
    if (!allowMultipleInstances) {
//            checking if fragment is present in back stack
        if (supportFragmentManager.backStackEntryCount > 0 &&
            supportFragmentManager.getBackStackEntryAt(
                supportFragmentManager.backStackEntryCount - 1
            ).name?.equals(
                fragment::class.java.name
            )!!
        )
            return
//            checkin if fragment is not in backstack but present is fragments list meaning fragment is added but not placed in backstack
        if (supportFragmentManager.fragments.size > 0 &&
            supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]::class.java.name == fragment::class.java.name
        )
            return
    }

    supportFragmentManager.transact {
        if (addBackStack)
            addToBackStack(fragment::class.java.name)
        if (clearBackStack) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
        }

        if (popBackStackInclusive != null) {
            supportFragmentManager.popBackStack(
                popBackStackInclusive,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        replace(frameId, fragment)
    }



    fun AppCompatActivity.addFragment(
        fragment: Fragment,
        frameId: Int,
        addBackStack: Boolean = true,
        transactionView: View?,
        transactionName: String?
    ) {
        supportFragmentManager.transact {
            if (addBackStack)
                addToBackStack(fragment::class.java.name)

            if (transactionView == null || transactionName == null)
                add(frameId, fragment)
            else
                add(frameId, fragment).addSharedElement(transactionView, transactionName)
        }
    }



}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()

}