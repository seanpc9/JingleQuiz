package com.example.jinglequiz;

import java.io.IOException;
import android.net.Uri;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;

// -------------------------------------------------------------------------
/**
 * Subclass of MediaPlayer that is specifically designed to ease playing of
 * Jingles for JingleQuiz application.
 *
 * @author Divyansh Gupta (divyg), Sean Crenshaw (seanpc9), Jordan White
 *         (jordanrw)
 * @version 2014.12.01
 */
public class JinglePlayer extends MediaPlayer implements OnPreparedListener,
    OnCompletionListener {
	// ~ Fields ................................................................
	private GameScreen screen;
	private boolean paused;
	private boolean enabled;

	// ~ Constructors ..........................................................
	// ----------------------------------------------------------
	/**
	 * Create a new JinglePlayer object.
	 *
	 * @param appScreen is the GameScreen upon which Jingles should be played.
	 */
	public JinglePlayer(GameScreen appScreen) {
		screen = appScreen;
		paused = false;
		this.setOnPreparedListener(this);
		this.setOnCompletionListener(this);
	}

	// ~ Methods ...............................................................
	// ----------------------------------------------------------
	/**
	 * Enables the GUI whenever the MediaPlayer is finished playing a Jingle.
	 *
	 * @param mp is the MediaPlayer that has just completed playing a Jingle.
	 */
	@Override
	public void onCompletion(MediaPlayer mp) {
		enabled = true;
		screen.setGUIEnabled(enabled);
		this.stop();
		this.reset();
	}

	// ----------------------------------------------------------
	/**
	 * Disables the GUI whenever the MediaPlayer begins playing a Jingle.
	 *
	 * @param mp is the MediaPlayer that has just been prepared to play a
	 *            Jingle.
	 */
	@Override
	public void onPrepared(MediaPlayer mp) {
		this.start();
		enabled = false;
		screen.setGUIEnabled(enabled);
	}

	// ----------------------------------------------------------
	/**
	 * Play a Jingle that is passed in toPlay with the audio stored in the "raw"
	 * resource folder.
	 *
	 * @param toPlay is the Jingle that will be played.
	 */
	public void playJingle(Jingle toPlay) {
		String path = toPlay.getRawResourcePath(screen.getApplicationContext());
		this.playSoundResource(path);
	}

	// ----------------------------------------------------------
	/**
	 * Play any sound resource based on the path that is passed in.
	 *
	 * @param path is the location of the sound resource to play.
	 */
	public void playSoundResource(String path) {
		try {
			this.setAudioStreamType(AudioManager.STREAM_MUSIC);
			this.setDataSource(screen.getApplicationContext(), Uri.parse(path));
			this.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------
	/**
	 * Get if JinglePlayer has been paused or not.
	 *
	 * @return true if JinglePlayer has been paused. Otherwise, false.
	 */
	public boolean isPaused() {
		return paused;
	}

	// ----------------------------------------------------------
	/**
	 * Pauses the JinglePlayer, stopping it from playing a Jingle.
	 */
	@Override
	public void pause() {
		super.pause();
		paused = true;
	}

	// ----------------------------------------------------------
	/**
	 * Starts the JinglePlayer playing a Jingle.
	 */
	@Override
	public void start() {
		super.start();
		paused = false;
	}

	// ----------------------------------------------------------
	/**
	 * Return the value of enabled.
	 *
	 * @return true if the GUI is enabled, false if it is not
	 */
	public boolean getEnabled() {
		return enabled;
	}
}
