//n la nam nhuan khi chia het cho 400 hoac chia het cho 4 nhung khong chia het cho 100.

let check = document.getElementById("checkBTN");
check.addEventListener("click", CheckYear)

function CheckYear(){
    let year = document.getElementById("leapyear").value;
    if (year === ""){
        window.alert("Please enter a year you want to check.");
    }
    else if(year < 0){
        window.alert("Please enter a valid year above 0");
    }
    else{
       checkleapyear()
    }
}

function checkleapyear() {
    let year = document.getElementById("leapyear").value;
    if ((year % 400 === 0) || (year % 4 === 0) && (year % 100 !== 0)) {
        document.getElementById("result").innerHTML = "The year " + year + " is a leap year."
    } else {
        document.getElementById("result").innerHTML = "The year " + year + " is not a leap year."
    }
}