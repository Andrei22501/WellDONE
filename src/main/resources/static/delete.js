function deleteModal(id) {
    fetch(url + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(us => {
            document.getElementById('idDelete').value = us.id;
            document.getElementById('nameDelete').value = us.name;
            document.getElementById('lastNameDelete').value = us.lastname;
            document.getElementById('ageDelete').value = us.age;
            document.getElementById('passwordDelete').value = us.password;
            document.getElementById('emailDelete').value = us.email;
        })
    });
}

const deleteUser = document.getElementById("deleteUser");
deleteUser.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(url + document.getElementById('idDelete').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(() => {
        document.getElementById("closeDeleteForm").click();
        getAllUsers()
    })
})
