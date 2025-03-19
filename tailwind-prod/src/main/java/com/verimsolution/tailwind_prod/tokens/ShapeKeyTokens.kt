package com.verimsolution.tailwind_prod.tokens

/**
 * Enum class définissant les tokens pour les formes (coins arrondis) dans le système de design.
 * Chaque token représente une taille ou une configuration de coin pour les composants UI.
 */
enum class ShapeKeyTokens {
    // Coins uniformes (tous les côtés)
    None,              // Pas d’arrondi (0dp, équivalent à Tailwind rounded-none)
    ExtraSmall,        // Très petit arrondi (ex. 4dp, Tailwind rounded-sm)
    Small,             // Petit arrondi (ex. 8dp, Tailwind rounded)
    Medium,            // Arrondi moyen (ex. 12dp, Tailwind rounded-md)
    Large,             // Grand arrondi (ex. 16dp, Tailwind rounded-lg)
    ExtraLarge,        // Très grand arrondi (ex. 24dp, Tailwind rounded-xl)
    Full,              // Arrondi complet (ex. 999dp, Tailwind rounded-full)

    // Coins spécifiques (variations par côté ou groupe de côtés)
    ExtraSmallTop,     // Très petit arrondi uniquement en haut (ex. 4dp top)
    SmallTop,          // Petit arrondi uniquement en haut (ex. 8dp top)
    MediumTop,         // Arrondi moyen uniquement en haut (ex. 12dp top)
    LargeTop,          // Grand arrondi uniquement en haut (ex. 16dp top)
    ExtraLargeTop,     // Très grand arrondi uniquement en haut (ex. 24dp top)

    ExtraSmallEnd,     // Très petit arrondi sur les côtés de fin (ex. droite en LTR, gauche en RTL)
    SmallEnd,          // Petit arrondi sur les côtés de fin (ex. 8dp end)
    MediumEnd,         // Arrondi moyen sur les côtés de fin (ex. 12dp end)
    LargeEnd,          // Grand arrondi sur les côtés de fin (ex. 16dp end)
    ExtraLargeEnd,     // Très grand arrondi sur les côtés de fin (ex. 24dp end)

    ExtraSmallStart,   // Très petit arrondi sur les côtés de début (ex. gauche en LTR, droite en RTL)
    SmallStart,        // Petit arrondi sur les côtés de début (ex. 8dp start)
    MediumStart,       // Arrondi moyen sur les côtés de début (ex. 12dp start)
    LargeStart,        // Grand arrondi sur les côtés de début (ex. 16dp start)
    ExtraLargeStart,   // Très grand arrondi sur les côtés de début (ex. 24dp start)
    CornerFull,
}