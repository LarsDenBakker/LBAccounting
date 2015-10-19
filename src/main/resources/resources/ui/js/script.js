function recordToFilename() {
   var input = document.getElementById('filename'),
           fileName = input.value;
   if (fileName) {
      alert('Recording: ' + fileName);
      // Recorder.record('audio', fileName);
   } else {
      alert('Please enter a filename!');
      input.focus();
   }
}