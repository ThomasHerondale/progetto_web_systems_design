
const facilitis = [
    new Facility(1, 'Mothers laugh showcase aggressive amanda avenue course',
        38.11422601344605, 13.364700784279123, 'ACTIVE', '1'),
    new Facility(2, 'Shoes prefer expensive stack paste adsl upload',
        38.109464991746805, 13.362469186579995, 'UNACTIVE', '2'),
    new Facility(3, 'Seafood participant intervention contrary located shock fruits',
        38.12216320330426, 13.360901448489713, 'UNACTIVE', '3')
]

const schedules = [
    new Schedule('1', 'xml_files/programme_schedule1.xml'),
    new Schedule('2', 'xml_files/programme_schedule2.xml'),
    new Schedule('3', 'xml_files/programme_schedule3.xml')
]

/**
 * populateTable è la funzione che si occupa di popolare la tabella dinamicamente.
 *
 * Riceve una lista di oggetti Facility
 * @param {Facility[]} listOfFacilitis
 * */
function populateTable(listOfFacilitis) {

    const body = document.getElementById('body')

    listOfFacilitis.forEach(
        facility => {

            const row = document.createElement('tr') // Per ogni oggetto Facility, definisco una riga

            const statusElement = document.createElement('td') // Definisco elemento Table Data, che conterrà la mia box
            const statusOfFacility = document.createElement('div')
            statusOfFacility.className = facility.status === 'ACTIVE' ? 'status-active' : 'status-unactive'
            statusOfFacility.textContent = facility.status

            statusElement.appendChild(statusOfFacility)
            row.appendChild(statusElement)


            const statusName = document.createElement('td')
            statusName.innerText = 'Impianoto ' + facility.id
            statusName.onclick = () => showPopup(facility)
            row.appendChild(statusName)


            body.appendChild(row)

        }
    );
}


function showPopup(facility) {
    console.log('funziona')
}

populateTable(facilitis)