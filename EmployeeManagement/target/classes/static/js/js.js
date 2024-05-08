// scripts.js

document.addEventListener('DOMContentLoaded', function() {
    // Voeg een click event listener toe aan de tabelrijen
    const tableRows = document.querySelectorAll('.table tbody tr');
    tableRows.forEach(row => {
        row.addEventListener('click', function() {
            // Voeg een klasse toe aan de geselecteerde rij voor visueel effect
            tableRows.forEach(row => row.classList.remove('selected'));
            this.classList.add('selected');

            // Haal de ID op van de geselecteerde rij en toon een alert
            const employeeId = this.querySelector('td:first-child').textContent;
            alert('Geselecteerde medewerker ID: ' + employeeId);
        });
    });
});
