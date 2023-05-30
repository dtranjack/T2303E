function Check(){
    CheckEmail();
    CheckPass();
}
function CheckEmail(){
    let x = document.getElementById("email").value
    if (x === ""){
        window.alert("Email input section cannot be blank")
    }
}

function CheckPass(){
    let x = document.getElementById("pass").value
    if (x === ""){
        window.alert("Password input section cannot be blank")
    }
}

