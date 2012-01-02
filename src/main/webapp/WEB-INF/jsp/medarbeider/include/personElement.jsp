<li><article class="searchPerson">
    <a href="/medarbeidere/${person.username}"><img src="/image/medarbeider_${person.username}.jpg?height=150" alt="${person.firstname} ${person.surname}"></a>
    <a href="/medarbeidere/${person.username}">${person.fullName}</a>
    <label for="${person.username}mail">Epost:</label>
    <a href="mailto:${person.username}@underdusken.no" id="${person.username}mail">${person.username}@underdusken.no</a>
    <label for="${person.username}tel">Telefon:</label>
    <a href="tel:${person.phoneNumber}" id="${person.username}tel">${person.phoneNumber}</a>
</article></li>