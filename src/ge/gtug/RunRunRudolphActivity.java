package ge.gtug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RunRunRudolphActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    Button start = (Button) findViewById(R.id.start);
    
    start.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent gameIntent = new Intent("ge.gtug.GAME");
			startActivity(gameIntent);
		}
	});
    }
}