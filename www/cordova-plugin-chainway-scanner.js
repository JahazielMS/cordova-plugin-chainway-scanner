var execute = require('cordova/exec');

var chainwayScan = {
  coolMethod: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'coolMethod', [])
  }
}

module.exports = chainwayScan