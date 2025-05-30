document.addEventListener('DOMContentLoaded', function() {
    // Sidebar toggle
    document.querySelector('.js-sidebar-toggle').addEventListener('click', function() {
        document.querySelector('.sidebar').classList.toggle('active');
        document.querySelector('.main').classList.toggle('active');
    });

    // Close sidebar when clicking outside on mobile
    document.addEventListener('click', function(e) {
        const sidebar = document.querySelector('.sidebar');
        const toggle = document.querySelector('.js-sidebar-toggle');
        
        if (window.innerWidth <= 768) {
            if (!sidebar.contains(e.target) && !toggle.contains(e.target) && sidebar.classList.contains('active')) {
                sidebar.classList.remove('active');
                document.querySelector('.main').classList.remove('active');
            }
        }
    });

    // Handle window resize
    window.addEventListener('resize', function() {
        if (window.innerWidth > 768) {
            document.querySelector('.sidebar').classList.remove('active');
            document.querySelector('.main').classList.remove('active');
        }
    });
}); 