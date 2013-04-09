package ge.gtug;

import ge.gtug.helpers.GameHelper;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {
	// final EditText edName = (EditText) findViewById(R.id.edName);
	GameHelper players;
	public TextView result, time;
	public ImageView /* first, second, third, fourth, fifth, sixth, */seventh;
	int[] drawableIds = { R.drawable.pirveli, R.drawable.meore,
			R.drawable.mesame, R.drawable.meotxe, R.drawable.mexute,
			R.drawable.meeqvse, R.drawable.meshvide };
	public int counter = 0;
	private CountDownTimer timer;
	MediaPlayer song;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		players = new GameHelper(this);
		Bundle bundle = getIntent().getExtras();

		String player = bundle.getString("name");
		String sound = bundle.getString("sound");
		System.out.println(sound + " <----- soundd");

		song = MediaPlayer.create(this, R.raw.rudolph);
		song.setVolume(100, 100);
		if (sound.equals("ON")) {
			song.start();
		}
		result = (TextView) findViewById(R.id.result);
		result.setText("Steps made: " + counter);

		seventh = (ImageView) findViewById(R.id.meshvide);
		time = (TextView) findViewById(R.id.time);
		final MyCounter timer = new MyCounter(10000, 1000);
		timer.start();

	}

	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction();

		Bundle bundle = getIntent().getExtras();

		String sound = bundle.getString("sound");

		switch (eventaction) {
		case MotionEvent.ACTION_DOWN:
			// finger touches the screen
			result.setText("Steps made: " + ++counter);

			break;
		case MotionEvent.ACTION_MOVE:
			// finger moves on the screen
			break;
		case MotionEvent.ACTION_UP:
			// finger leaves the screen
			seventh.setImageResource(drawableIds[counter % drawableIds.length]);
			break;
		}

		// tell the system that we handled the event and no further processing
		// is required
		return true;
	}

	public class MyCounter extends CountDownTimer {
		public MyCounter(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		private void updateInDb(int counter) {

			Bundle extras = getIntent().getExtras();
			String player = "";
			if (extras != null) {
				player += extras.getString("name");
			}

			players.updateScores(counter, player);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			time.setText((millisUntilFinished / 1000) + "");
		}

		@Override
		public void onFinish() {
			song.stop();
			song.release();
			time.setText("");
			Bundle bundle = getIntent().getExtras();

			String player = bundle.getString("name");

			Toast.makeText(getBaseContext(),
					"Time is up! Your score is: " + counter, 5000).show();

			Intent resultIntent = new Intent("ge.gtug.RESULTS");
			resultIntent.putExtra("name", player);
			resultIntent.putExtra("score", counter);
			updateInDb(counter);
			startActivity(resultIntent);
			finish();
		}
	}

}
