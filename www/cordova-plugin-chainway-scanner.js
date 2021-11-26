var execute = require('cordova/exec');

var chainwayScan = {
  coolMethod: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'coolMethod', [])
  },
  start: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'start', [])
  }
}

module.exports = chainwayScan