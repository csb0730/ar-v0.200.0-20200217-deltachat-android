package org.thoughtcrime.securesms;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.thoughtcrime.securesms.util.DynamicLanguage;
import org.thoughtcrime.securesms.util.DynamicTheme;
import org.thoughtcrime.securesms.util.Util;

public class LogViewActivity extends BaseActionBarActivity {

  private static final String TAG = LogViewActivity.class.getSimpleName();

  private final DynamicTheme    dynamicTheme    = new DynamicTheme();
  private final DynamicLanguage dynamicLanguage = new DynamicLanguage();

  LogViewFragment logViewFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.log_view_activity);
    logViewFragment = LogViewFragment.newInstance();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container, logViewFragment);
    transaction.commit();
    dynamicTheme.onCreate(this);
    dynamicLanguage.onCreate(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  protected void onResume() {
    super.onResume();
    dynamicTheme.onResume(this);
    dynamicLanguage.onResume(this);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    MenuInflater inflater = this.getMenuInflater();
    menu.clear();

    inflater.inflate(R.menu.view_log, menu);

    super.onPrepareOptionsMenu(menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;

      case R.id.copy_log_to_clipboard:
        Util.writeTextToClipboard(this, logViewFragment.getLogText());
        Toast.makeText(getApplicationContext(), R.string.done, Toast.LENGTH_SHORT).show();
        return true;
    }

    return false;
  }
}
