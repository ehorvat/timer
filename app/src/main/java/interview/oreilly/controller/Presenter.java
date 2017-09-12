package interview.oreilly.controller;

import android.os.Bundle;

/**
 * Created by michaeldunn on 7/11/16.
 *
 * Simple interface defining a small contract for classes that will present user interface components.
 */
public interface Presenter {

  /**
   * This callback will fire when the UI has been created, and never again.
   *
   * @param savedInstanceState the saved instance state from the activity
   */
  void uiCreated(Bundle savedInstanceState);

  /**
   * This callback will fire when the UI is attached to the window.
   */
  void uiAttached();

  /**
   * This callback will fire when the UI is detached from the window.
   */
  void uiDetached();

}
