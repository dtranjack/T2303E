var hour, minute, second, count
    hour = 0
    minute = 0
    second = 0
    count = 0
var timer = 0
function startimer(){
    timer = setInterval(stopWatch,10)
}

function pausetimer(){
    clearInterval(timer)
}

function resettimer(){
    clearInterval(timer)
    hour = 0
    minute = 0
    second = 0
    count = 0
    document.getElementById("hr").innerHTML = "00";
    document.getElementById('min').innerHTML = "00";
    document.getElementById('sec').innerHTML = "00";
    document.getElementById('milsec').innerHTML = "00";
}

function stopWatch(){
    count++;
    if(count === 100){
        second++;
        count = 0;
    }
    if (second === 60){
        minute++;
        second = 0;
    }
    if (minute === 60){
        hour++;
        minute = 0;
        second = 0;
    }

    let hrString = hour;
    let minString = minute;
    let secString = second;
    let countString = count;

    if (hour < 10) {
        hrString = "0" + hrString;
    }

    if (minute < 10) {
        minString = "0" + minString;
    }

    if (second < 10) {
        secString = "0" + secString;
    }

    if (count < 10) {
        countString = "0" + countString;
    }
    document.getElementById("hr").innerHTML = hrString
    document.getElementById('min').innerHTML = minString;
    document.getElementById('sec').innerHTML = secString;
    document.getElementById('milsec').innerHTML = countString;
}


