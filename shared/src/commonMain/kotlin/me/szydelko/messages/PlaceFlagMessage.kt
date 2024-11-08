package me.szydelko.messages

enum class PlaceFlagMessage(val en: String, val pl: String) {
    SEATING_AVAILABLE("Seating available", "Dostępne miejsca do siedzenia"),
    SHELTER_AVAILABLE("Shelter available for rain or sun", "Dostępne schronienie przed deszczem lub słońcem"),
    SHADED_AREA("Shaded area available", "Dostępny zacieniony obszar"),
    BATHROOM_AVAILABLE("Bathrooms available nearby", "Dostępne toalety w pobliżu"),
    WATER_SOURCE("Drinking water source available", "Dostępne źródło wody pitnej"),
    PICNIC_AREA("Picnic area with tables available", "Dostępne miejsce na piknik ze stołami"),
    CAMPFIRE_ALLOWED("Campfires are allowed", "Ogniska są dozwolone"),
    PARKING_AVAILABLE("Parking available nearby", "Parking dostępny w pobliżu"),
    PET_FRIENDLY("Pets are allowed", "Zwierzęta są dozwolone"),
    PLAYGROUND("Playground for children available", "Dostępny plac zabaw dla dzieci"),
    TRAIL_ACCESS("Access to hiking or walking trails", "Dostęp do szlaków turystycznych"),
    ACCESSIBLE("Accessible for people with disabilities", "Dostępne dla osób niepełnosprawnych"),
    BIKE_RACKS("Bike racks available", "Dostępne stojaki na rowery"),
    ELECTRICITY_AVAILABLE("Electricity available", "Dostępna elektryczność"),
    BBQ_GRILL("BBQ grill available", "Dostępny grill"),

    FLORAL_SCENT("Pleasant floral scent", "Przyjemny zapach kwiatów"),
    FRESH_AIR("Fresh air", "Świeże powietrze"),
    PINE_SCENT("Scent of pine trees", "Zapach sosen"),
    SEA_BREEZE("Fresh sea breeze scent", "Świeży zapach morskiej bryzy"),

    MUDDY("Ground is muddy", "Ziemia jest błotnista"),
    LITTERED("Area is littered with trash", "Obszar jest zaśmiecony"),
    SMELLY("Area has an unpleasant smell", "Obszar ma nieprzyjemny zapach"),
    NOISY("Area is very noisy", "Obszar jest bardzo hałaśliwy"),
    FLOODED("Area is flooded or has standing water", "Obszar jest zalany lub ma stojącą wodę"),
    INSECTS_PRESENT("Area has many insects", "Obszar ma wiele owadów"),
    UNSAFE_AREA("Area is considered unsafe", "Obszar jest uznawany za niebezpieczny"),
    OVERCROWDED("Area is often overcrowded", "Obszar jest często zatłoczony"),
    POOR_LIGHTING("Poor lighting in the area", "Słabe oświetlenie w obszarze");
}