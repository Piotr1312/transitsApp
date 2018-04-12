app.filter('secondsToTime', function() {

    function padTime(t) {
        return t < 10 ? "0"+t : t;
    }

    return function(_seconds) {
        if (typeof _seconds !== "number" || _seconds < 0)
            return "00:00:00";

        var hours = Math.floor(_seconds / 3600);
        var minutes = Math.floor((_seconds % 3600) / 60);

        return padTime(hours) + ":" + padTime(minutes);
    };
});
