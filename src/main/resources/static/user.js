const data = document.getElementById("user-table");
const url = 'http://localhost:8080/api/userInfo';

function userAuthInfo() {
    fetch(url)
        .then((res) => res.json())
        .then((u) => {
            let temp = '';
            temp += `<tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.lastname}</td>
            <td>${u.age}</td>
            <td>${u.email}</td>
            <td>${u.roles.map((el)=> el.name.toString())}</td>
            </tr>`;
            data.innerHTML = temp;
        });
}

userAuthInfo()
