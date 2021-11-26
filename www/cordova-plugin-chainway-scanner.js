var execute = require('cordova/exec');

var chainwayScan = {
  coolMethod: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'coolMethod', [])
  },
  start: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'startScan', [])
  },
  stop: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'stopScan', [])
  },
  open: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'open', [])
  },
  close: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'close', [])
  }
}

module.exports = chainwayScan