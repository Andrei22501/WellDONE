const url = 'http://localhost:8080/api/admin';
const renderTable = document.getElementById("allUsers");

const renderPosts = (allUsers) => {
    let temp = '';
    allUsers.forEach((u) => {
        temp += `<tr>
                                <td>${u.id}</td>
                                <td>${u.name}</td>
                                <td>${u.lastname}</td>
                                <td>${u.age}</td>
                                <td>${u.email}</td>
                                <td>${u.roles.map((el)=> el.name.toString())}</td>
                                <td>  
                                <button class="btn btn-info" type="button"
                                data-bs-toggle="modal" data-bs-target="#modalEdit"
                                onclick="editModal(${u.id})">Edit</button></td>
                                <td>
                                <button class="btn btn-danger" type="button"
                                data-bs-toggle="modal" data-bs-target="#modalDelete"
                                onclick="deleteModal(${u.id})">Delete</button></td>
                                </tr>
                                `
    })
    renderTable.innerHTML = temp;
}

function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            renderPosts(data)
        })
}

getAllUsers()


const tableForUser = document.getElementById("tableForUser");
const urlAuth = 'http://localhost:8080/api/userInfo';

function userAuthInfo() {
    fetch(urlAuth)
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
            tableForUser.innerHTML = temp;
        });
}

userAuthInfo()


