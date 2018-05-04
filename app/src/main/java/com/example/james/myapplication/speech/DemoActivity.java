
/*
<uses-permission android:name=“android.permission.INTERNET” /> //online mode

<uses-permission android:name=“android.permission.RECORD_AUDIO” />




 */
/*
try {

startActivityForResult(intent, RESULT_SPEECH);
} catch (ActivityNotFoundException a) {

Toast.makeText(getApplicationContext(),

“Opps! Your device doesn’t support Speech to Text”,Toast.LENGTH_SHORT).show();
}

The RecognizerIntent will convert the speech input to text and send back the result as ArrayList with key RecognizerIntent.EXTRA_RESULTS.

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
super.onActivityResult(requestCode, resultCode, data);
switch (requestCode) {
case RESULT_SPEECH: {
if (resultCode == RESULT_OK && null != data) {
ArrayList<String> text = data
.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

}break;
}
}
}
 */
/*
package com.example.james.myapplication.speech;

Implement listener class

public class DemoActivity extends Activity implements RecognitionListener {

    Declare a variable and initialize it

    SpeechRecognizer speech = SpeechRecognizer.createSpeechRecognizer(getActivity());

speech.setRecognitionListener(this);

    recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,

            “en”);

recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,

    getActivity().getPackageName());

recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,

    RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

    Implement methods :

    @Override

    public void onBeginningOfSpeech() {

    }

    @Override

    public void onBufferReceived(byte[] buffer) {

    }

    @Override

    public void onEndOfSpeech() {

    }
    @Override

    public void onError(int errorCode) {

        switch (errorCode) {

            case SpeechRecognizer.ERROR_AUDIO:

                message = R.string.error_audio_error;

                break;

            case SpeechRecognizer.ERROR_CLIENT:

                message = R.string.error_client;

                break;

            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:

                message = R.string.error_permission;

                break;

            case SpeechRecognizer.ERROR_NETWORK:

                message = R.string.error_network;

                break;

            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:

                message = R.string.error_timeout;

                break;

            case SpeechRecognizer.ERROR_NO_MATCH:

                message = R.string.error_no_match;

                break;

            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:

                message = R.string.error_busy;

                break;

            case SpeechRecognizer.ERROR_SERVER:

                message = R.string.error_server;

                break;

            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:

                message = R.string.error_timeout;

                break;

            default:

                message = R.string.error_understand;

                break;
        }
    }

    @Override

    public void onEvent(int arg0, Bundle arg1) {
    }

    @Override

    public void onPartialResults(Bundle arg0) {
    }

    @Override

    public void onReadyForSpeech(Bundle arg0) {
    }

    @Override

    public void onResults(Bundle results) {

        ArrayList<String> matches = results

                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

    }

    @Override

    public void onRmsChanged(float rmsdB) {

    }*/