var recorder;
var audio = document.querySelector('audio');
function startRecording() {
	HZRecorder.get(function(rec) {
		recorder = rec;
		recorder.start();
	});
}
function stopRecording() {
	recorder.stop();
}
function playRecording() {
	recorder.play(audio);
}
function uploadAudio() {
	recorder.upload("uploadServlet", function(state, e) {
		switch (state) {
		case 'uploading':
			// var percentComplete = Math.round(e.loaded * 100 / e.total) + '%';
			break;
		case 'ok':
			//alert("上传成功");
			/* window.location.href="VideoSearchServlet"; */
			break;
		case 'error':
			alert("上传失败");
			break;
		case 'cancel':
			alert("上传被取消");
			break;
		}
	});
}