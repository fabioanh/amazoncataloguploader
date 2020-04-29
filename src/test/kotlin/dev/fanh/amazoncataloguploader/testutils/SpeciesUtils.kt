package dev.fanh.amazoncataloguploader.testutils

import dev.fanh.amazoncataloguploader.data.LanguagedValue
import dev.fanh.amazoncataloguploader.data.LocalisedValue
import dev.fanh.amazoncataloguploader.data.Species

fun getEmptyCommonNamesSpecies(): Species {
    return Species(version = "V1",
            id = "idAnimal1",
            kingdom = "animalia",
            commonNames = ArrayList(),
            description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
            fullDescription = "The stripped jaguar is very difficult to spot as it hides in the middle of Zebra groups. Some tribes refer to it as a wolf in sheep's clothing",
            scientificName = "Jaguarus Felinus Rayadus")

}

fun getNoCommonNamesSpecies(): Species {
    return Species(version = "V1",
            id = "idAnimal1",
            kingdom = "animalia",
            description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
            fullDescription = "The stripped jaguar is very difficult to spot as it hides in the middle of Zebra groups. Some tribes refer to it as a wolf in sheep's clothing",
            scientificName = "Jaguarus Felinus Rayadus")

}

fun getBasicSpecies(): Species {
    return Species(version = "V1",
            id = "idAnimal1",
            kingdom = "animalia",
            commonNames = listOf(LanguagedValue("stripped jaguar", "en"),
                    LanguagedValue("jaguar rayado", "es"),
                    LanguagedValue("Jaguarayado", "unknown")),
            description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
            fullDescription = "The stripped jaguar is very difficult to spot as it hides in the middle of Zebra groups. Some tribes refer to it as a wolf in sheep's clothing",
            scientificName = "Jaguarus Felinus Rayadus")
}

fun getBasicSpeciesAnt(): Species {
    return Species(version = "V1",
            id = "idAnimal2",
            kingdom = "animalia",
            commonNames = listOf(LanguagedValue("assassin ant", "en"),
                    LanguagedValue("Attentöterameise", "de"),
                    LanguagedValue("hormigota malota", "unknown"),
                    LanguagedValue("fourmi assassine", "fr"),
                    LanguagedValue("hormiga asesina", "es")),
            description = "Very dangerous ant that bites and won't ever release its jaws. Its venom dissolves skin going to the blood and reaching all the victim's body",
            fullDescription = "The assassin ant is a very dangerous ant found in the bank of the Amazon small lakes. It is known to be the only ant who hunts without help of their mate ants",
            scientificName = "Antus Assassinous")
}

fun getNoFullDescriptionSpecies(): Species {
    return Species(version = "V1",
            id = "idAnimal1",
            kingdom = "animalia",
            commonNames = listOf(LanguagedValue("stripped jaguar", "en"),
                    LanguagedValue("jaguar rayado", "es"),
                    LanguagedValue("Jaguarayado", "unknown")),
            description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
            scientificName = "Jaguarus Felinus Rayadus")
}

fun getCompleteSpecies(): Species {
    return Species(version = "V1",
            id = "idAnimal1",
            kingdom = "animalia",
            commonNames = listOf(LanguagedValue("stripped jaguar", "en"),
                    LanguagedValue("jaguar rayado", "es"),
                    LanguagedValue("Jaguarayado", "unknown")),
            description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
            fullDescription = "The stripped jaguar is very difficult to spot as it hides in the middle of Zebra groups. Some tribes refer to it as a wolf in sheep's clothing",
            scientificName = "Jaguarus Felinus Rayadus",
            behaviour = "This animal behaves wildly. Simultánea",
            endangeredStatus = listOf(LocalisedValue("En peligro crítico (CR)", "co"), LocalisedValue("Preocupación menor (LC)", "Global")),
            feeding = "Eats only aquatic zebras from the Peruvian Amazon",
            habitat = "Amazon river bank",
            imageURLs = listOf("https://d2ouvy59p0dg6k.cloudfront.net/img/original/original_ww2137556.jpg",
                    "https://url2.com/img2.jpg",
                    "https://url1.com/img1.jpg",
                    "https://url5.com/img5.jpg",
                    "https://url3.com/img3.jpg",
                    "https://url4.com/img4.jpg"),
            lifecycle = "Its reproduction cycle is tied to the pink dolphins dancing habits",
            lifeForm = "Hábito: It's mostly active during the evenings",
            migration = "Doesn't present migratory habits as it can eat aquatic zebras all year long",
            reproduction = "Creates nests on top of the tallest Ceibas")
}