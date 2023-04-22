var time = new Date();
function testonload(){
    getHour()
    getMinute()
    getSecond()
}

function getHour(){
    let hr = time.getHours()
    console.log(hr)
    if (hr < 12){
        if(hr === 0){
            document.getElementById("hour").innerHTML = 12
            document.getElementById("hour").innerHTML = "AM"
        }
        else {
            document.getElementById("hour").innerHTML = hr
            document.getElementById("ampm").innerHTML = "AM"
        }
    }
    else if (hr === 12){
        document.getElementById("hour").innerHTML = hr - 12
        document.getElementById("ampm").innerHTML = "PM"
    }
    else{
        document.getElementById("hour").innerHTML = hr - 12
        document.getElementById("ampm").innerHTML = "PM"
    }
}

function getMinute(){
    let min = time.getMinutes()
    document.getElementById("minute").innerHTML = min
    console.log(min)
}

function  getSecond(){
    let sec = time.getSeconds()
    document.getElementById("second").innerHTML = sec
    console.log(sec)
}