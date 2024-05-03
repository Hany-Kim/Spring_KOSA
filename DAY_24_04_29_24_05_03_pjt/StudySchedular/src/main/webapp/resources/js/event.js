document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const memberItems = document.querySelectorAll('.member-card');

    searchInput.addEventListener('input', function() {
        const searchKeyword = this.value.trim().toLowerCase();
        
        memberItems.forEach(function(item) {
            const memberName = item.querySelector('.member-name').textContent.trim().toLowerCase();
            
            if (memberName.includes(searchKeyword)) {
                item.style.display = 'flex';
            } else {
                item.style.display = 'none';
            }
        });
    });

    // 에러 메시지가 있는 경우
    const errorMessage = document.getElementById('error-message');
    if (errorMessage) {
        setTimeout(function() {
            errorMessage.style.opacity = '0';
        }, 2000); // 2초 후에 메시지가 점점 사라짐
    }
});
