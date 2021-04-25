function Error() {
    Swal.fire({
        title: 'Error!',
        text: 'Do you want to continue',
        icon: 'error',
        confirmButtonText: 'Cool'
    })
}

function Success() {
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Thank you for joining our newsletter',
        showConfirmButton: false,
        timer: 1500
    })
}