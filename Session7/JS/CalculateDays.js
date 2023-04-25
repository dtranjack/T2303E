const date = new Date();
let day = date.getDate()
let month = date.getMonth() + 1
let year = date.getFullYear()
let xmasyear = date.getFullYear()
if (month === 12 && day > 25){
    xmasyear++;
}

let xmasdate = new Date(xmasyear, 12, 25);

let msperday = 1000 * 60 * 60 * 24

let remainingdays = Math.ceil((xmasdate.getTime() - date.getTime())/msperday)
console.log(remainingdays)

document.getElementById("result").innerHTML = "There are " + remainingdays + " days left until Christmas"
