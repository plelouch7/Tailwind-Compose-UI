package com.verimsolution.tailwind_prod.tokens

/**
 * Enum class définissant les clés pour les styles typographiques dans le système de design,
 * optimisée pour les applications mobiles Android selon les recommandations Material Design.
 * Les tailles et catégories sont adaptées à la lisibilité sur écran mobile et compatibles avec Tailwind CSS.
 */
enum class TypographyKeyTokens {
    // --- Display (Titres très imposants, rarement utilisés sur mobile sauf pour grandes accroches) ---
    DisplayLarge,   // Titre très grand (ex. 96sp, Material H1 mobile ajusté, Tailwind text-6xl)
    DisplayMedium,  // Titre grand (ex. 60sp, Material H2 mobile ajusté, Tailwind text-5xl)
    DisplaySmall,   // Titre moyen (ex. 48sp, Material H3 mobile ajusté, Tailwind text-4xl)

    // --- Headline (Titres principaux ou intertitres, adaptés à Android mobile) ---
    HeadlineLarge,  // Titre principal large (ex. 34sp, Material H4 mobile, Tailwind text-2xl)
    HeadlineMedium, // Titre principal moyen (ex. 24sp, Material H5 mobile, Tailwind text-xl)
    HeadlineSmall,  // Titre principal petit (ex. 20sp, Material H6 mobile, Tailwind text-lg)

    // --- Title (Titres de sections ou sous-titres, couramment utilisés sur mobile) ---
    TitleLarge,     // Titre de section large (ex. 16sp en gras, Material Subtitle 1, Tailwind text-base)
    TitleMedium,    // Titre de section moyen (ex. 14sp en moyen, Material Subtitle 2, Tailwind text-sm)
    TitleSmall,     // Titre de section petit (ex. 12sp en moyen, ajusté pour mobile, Tailwind text-xs)

    // --- Body (Texte principal pour le contenu, optimisé pour la lecture sur mobile) ---
    BodyLarge,      // Texte principal large (ex. 16sp, Material Body 1, Tailwind text-base)
    BodyMedium,     // Texte principal moyen (ex. 14sp, Material Body 2, Tailwind text-sm)
    BodySmall,      // Texte principal petit (ex. 12sp, ajusté pour mobile, Tailwind text-xs)

    // --- Label (Étiquettes ou textes courts, pour boutons ou annotations) ---
    LabelLarge,     // Étiquette large (ex. 14sp en moyen, Material Button, Tailwind text-sm)
    LabelMedium,    // Étiquette moyenne (ex. 12sp en moyen, ajusté pour mobile, Tailwind text-xs)
    LabelSmall,     // Étiquette petite (ex. 11sp en moyen, ajusté pour petits éléments, Tailwind text-2xs)

    // --- Caption & Overline (Textes secondaires ou décoratifs, standards Android) ---
    Caption,        // Légende ou note secondaire (ex. 12sp, Material Caption, Tailwind text-xs)
    Overline        // Texte en majuscules ou surlignage (ex. 10sp, Material Overline, Tailwind text-2xs)
}