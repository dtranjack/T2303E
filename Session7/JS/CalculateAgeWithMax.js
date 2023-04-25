const date = new Date();
let year = date.getFullYear()

yob.max = year;

function checkYear(){
    yearofbirth = document.getElementById("yob").value;
    if (yearofbirth === ""){
        window.alert("Please enter your year of birth")
    }
    else if (yearofbirth > year){
        window.alert("Year entered is not valid")
        document.getElementById("yob").value = "";
    }
    else{
        CalculateAge();
    }
}

function CalculateAge(){
    let age = year - yearofbirth;
    document.getElementById("result").innerHTML = "Your age is: " + age;
}

