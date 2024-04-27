console.log("register loaded!")

function register() {
    const name = document.getElementById("name").value
    const phone = document.getElementById("phone").value
    const email = document.getElementById("email").value
    const passowrd = document.getElementById("password").value
    const token = 'xxxxx'

    let headers = {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Authorization": `Bearer ${token}`,
    }
    let requestBody = {"name":name,"phone":phone,"email":email,"password":passowrd}

    fetch("http://localhost:8080/api/member", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(requestBody)
    })
        .then((response) => {
            console.log("Status = " + response.status);
            return response.text(); // 解析JSON格式的回應
        })
        .then((data) => {
            alert("狀態 = " + data); // 使用回傳的數據
            if (data === "Success") {
                location.href = "http://localhost:8080/login";
            }
        })
        .catch(json => {
            console.log(json)
        });

    document.getElementById("name").value = ""
    document.getElementById("phone").value = ""
    document.getElementById("email").value = ""
    document.getElementById("password").value = ""

}

function login() {
    const email = document.getElementById("email").value
    const passowrd = document.getElementById("password").value
    const token = 'xxxxx'

    let headers = {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Authorization": `Bearer ${token}`,
    }
    let requestBody = {"email":email,"password":passowrd}

    fetch("http://localhost:8080/api/member", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify(requestBody)
    })
        .then((response) => {
            console.log("Status = " + response.status)
            return response.text();
        })
        .then((data) => {
            alert("登入狀態 = " + data)
            if (data === "Success") {
                location.href = "http://localhost:8080/hello"
            }
        })
        .catch(json => {
            console.log(json)
            alert(json)
        });
}