const date = new Date();
let year = date.getFullYear()

function CalculateAge(){
    let yearofbirth = document.getElementById("yob").value;
    let age = year - yearofbirth;
    document.getElementById("result").innerHTML = "Your age is: " + age;
}