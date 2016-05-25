var calcBusinessDays = function (dDate1, dDate2) { // input given as Date objects
    var iWeeks, iDateDiff, iAdjust = 0;
    if (dDate2 < dDate1)
        return -1; // error code if dates transposed
    var iWeekday1 = dDate1.getDay(); // day of week
    var iWeekday2 = dDate2.getDay();
    iWeekday1 = (iWeekday1 == 0) ? 7 : iWeekday1; // change Sunday from 0 to 7
    iWeekday2 = (iWeekday2 == 0) ? 7 : iWeekday2;
    if ((iWeekday1 > 5) && (iWeekday2 > 5))
        iAdjust = 1; // adjustment if both days on weekend
    iWeekday1 = (iWeekday1 > 5) ? 5 : iWeekday1; // only count weekdays
    iWeekday2 = (iWeekday2 > 5) ? 5 : iWeekday2;

    // calculate differnece in weeks (1000mS * 60sec * 60min * 24hrs * 7 days = 604800000)
    iWeeks = Math.floor((dDate2.getTime() - dDate1.getTime()) / 604800000)

    if (iWeekday1 <= iWeekday2) {
        iDateDiff = (iWeeks * 5) + (iWeekday2 - iWeekday1)
    } else {
        iDateDiff = ((iWeeks + 1) * 5) - (iWeekday1 - iWeekday2)
    }

    iDateDiff -= iAdjust // take into account both days on weekend

    return (iDateDiff + 1); // add 1 because dates are inclusive
}
var calDay = function () {
    $timeType = $("input[name=timeType]:radio:checked");
    $total = $("#total");
    // Full
    if ($timeType.val() === "1") {
        var startP = $("#startAt").val().split("-");
        var startDate = new Date(startP[2], startP[1] - 1, startP[0]);

        var endP = $("#endAt").val().split("-");
        var endDate = new Date(endP[2], endP[1] - 1, endP[0]);
        console.log(startDate + ":" + endDate);
        if (startDate !== "Invalid Date" && endDate !== "Invalid Date") {
            var total = calcBusinessDays(startDate, endDate);
            console.log(total);
            if (total !== NaN && total !== -1) {
                $total.text(total + " ");
            }
        }
    } else if ($timeType.val() === "2" || $timeType.val() === "3") {
        $total.text("0.5 (ครึ่ง)");
    }
}

var selectChange = function () {
    $timeType = $("input[name=timeType]:radio");

    $timeType.change(function () {
        $this = $(this);
        if ($this.val() === "1") {
            $("#startAtA").prop("disabled", true);
            $("#startAtM").prop("disabled", true);
            $("#endAtA").prop("disabled", true);
            $("#endAtM").prop("disabled", true);
            $("#startAt").prop("disabled", false);
            $("#endAt").prop("disabled", false);
        } else if ($this.val() === "2") {
            $("#startAtM").prop("disabled", false);
            $("#startAtA").prop("disabled", true);
            $("#endAtA").prop("disabled", true);
            $("#endAtM").prop("disabled", false);
            $("#startAt").prop("disabled", true);
            $("#endAt").prop("disabled", true);
        } else if ($this.val() === "3") {
            $("#startAtA").prop("disabled", false);
            $("#startAtM").prop("disabled", true);
            $("#endAtA").prop("disabled", false);
            $("#endAtM").prop("disabled", true);
            $("#startAt").prop("disabled", true);
            $("#endAt").prop("disabled", true);
        }
        calDay();
    });
}