// TranquiloShop JavaScript

document.addEventListener('DOMContentLoaded', function() {
    // Auto-hide alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });

    // Form validation enhancements
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!form.checkValidity()) {
                e.preventDefault();
                e.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    });

    // Price input formatting
    const priceInputs = document.querySelectorAll('input[type="number"][step="0.01"]');
    priceInputs.forEach(input => {
        input.addEventListener('blur', function() {
            if (this.value) {
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
    });

    // Search form auto-submit on Enter
    const searchInputs = document.querySelectorAll('input[name="busca"]');
    searchInputs.forEach(input => {
        input.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                this.closest('form').submit();
            }
        });
    });

    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });

    // Loading state for forms
    const submitButtons = document.querySelectorAll('button[type="submit"]');
    submitButtons.forEach(button => {
        button.addEventListener('click', function() {
            const form = this.closest('form');
            if (form && form.checkValidity()) {
                this.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Processando...';
                this.disabled = true;
            }
        });
    });

    // Confirmation for delete actions
    const deleteButtons = document.querySelectorAll('button[data-bs-target*="deleteModal"]');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const productName = this.closest('.produto-card')?.querySelector('.card-title')?.textContent;
            if (productName) {
                const modal = document.querySelector(this.getAttribute('data-bs-target'));
                const productNameSpan = modal?.querySelector('.modal-body strong');
                if (productNameSpan) {
                    productNameSpan.textContent = productName;
                }
            }
        });
    });

    // Add loading animation to cards
    const cards = document.querySelectorAll('.produto-card');
    cards.forEach((card, index) => {
        card.style.animationDelay = `${index * 0.1}s`;
    });

    // Enhanced search functionality
    const searchForm = document.querySelector('form[action*="/produtos"]');
    if (searchForm) {
        const searchInput = searchForm.querySelector('input[name="busca"]');
        const clearButton = document.createElement('button');
        clearButton.type = 'button';
        clearButton.className = 'btn btn-outline-secondary';
        clearButton.innerHTML = '<i class="bi bi-x"></i>';
        clearButton.style.display = 'none';
        
        if (searchInput && searchInput.value) {
            clearButton.style.display = 'block';
        }
        
        searchInput?.addEventListener('input', function() {
            clearButton.style.display = this.value ? 'block' : 'none';
        });
        
        clearButton.addEventListener('click', function() {
            searchInput.value = '';
            searchForm.submit();
        });
        
        const inputGroup = searchInput?.closest('.input-group');
        if (inputGroup) {
            inputGroup.appendChild(clearButton);
        }
    }
});

// Utility functions
function formatCurrency(value) {
    return new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    }).format(value);
}

function showToast(message, type = 'success') {
    const toastContainer = document.querySelector('.toast-container') || createToastContainer();
    const toast = createToast(message, type);
    toastContainer.appendChild(toast);
    
    const bsToast = new bootstrap.Toast(toast);
    bsToast.show();
    
    toast.addEventListener('hidden.bs.toast', () => {
        toast.remove();
    });
}

function createToastContainer() {
    const container = document.createElement('div');
    container.className = 'toast-container position-fixed top-0 end-0 p-3';
    container.style.zIndex = '1055';
    document.body.appendChild(container);
    return container;
}

function createToast(message, type) {
    const toast = document.createElement('div');
    toast.className = `toast align-items-center text-white bg-${type} border-0`;
    toast.setAttribute('role', 'alert');
    toast.innerHTML = `
        <div class="d-flex">
            <div class="toast-body">
                ${message}
            </div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
        </div>
    `;
    return toast;
}