package com.verimsolution.tailwind_prod.tokens

import androidx.compose.ui.graphics.Color

internal object PaletteTokens {
    // Structure pour une famille de couleurs
    data class ColorFamily(
        val c50: Color, val c100: Color, val c200: Color, val c300: Color, val c400: Color,
        val c500: Color, val c600: Color, val c700: Color, val c800: Color, val c900: Color
    )

    // Palette Tailwind complète
    val Red = ColorFamily(
        c50 = Color(0xFFFEF2F2), c100 = Color(0xFFFEE2E2), c200 = Color(0xFFFECACA),
        c300 = Color(0xFFFCA5A5), c400 = Color(0xFFF87171), c500 = Color(0xFFEF4444),
        c600 = Color(0xFFDC2626), c700 = Color(0xFFB91C1C), c800 = Color(0xFF991B1B),
        c900 = Color(0xFF7F1D1D)
    )

    val Orange = ColorFamily(
        c50 = Color(0xFFFFF7ED), c100 = Color(0xFFFFEDD5), c200 = Color(0xFFFED7AA),
        c300 = Color(0xFFFDBA74), c400 = Color(0xFFFB923C), c500 = Color(0xFFF97316),
        c600 = Color(0xFFEA580C), c700 = Color(0xFFC2410C), c800 = Color(0xFF9A3412),
        c900 = Color(0xFF7C2D12)
    )

    val Amber = ColorFamily(
        c50 = Color(0xFFFFFBEB), c100 = Color(0xFFFEF3C7), c200 = Color(0xFFFDE68A),
        c300 = Color(0xFFFCD34D), c400 = Color(0xFFFBBF24), c500 = Color(0xFFF59E0B),
        c600 = Color(0xFFD97706), c700 = Color(0xFFB45309), c800 = Color(0xFF92400E),
        c900 = Color(0xFF78350F)
    )

    val Yellow = ColorFamily(
        c50 = Color(0xFFFFFBEB), c100 = Color(0xFFFEF3C7), c200 = Color(0xFFFDE68A),
        c300 = Color(0xFFFCD34D), c400 = Color(0xFFFBBF24), c500 = Color(0xFFF59E0B),
        c600 = Color(0xFFD97706), c700 = Color(0xFFB45309), c800 = Color(0xFF92400E),
        c900 = Color(0xFF78350F)
    )

    val Lime = ColorFamily(
        c50 = Color(0xFFF7FEE7), c100 = Color(0xFFECFCCB), c200 = Color(0xFFD9F99D),
        c300 = Color(0xFFBEF264), c400 = Color(0xFFA3E635), c500 = Color(0xFF84CC16),
        c600 = Color(0xFF65A30D), c700 = Color(0xFF4D7C0F), c800 = Color(0xFF3F6212),
        c900 = Color(0xFF365314)
    )

    val Green = ColorFamily(
        c50 = Color(0xFFF0FDF4), c100 = Color(0xFFDCFCE7), c200 = Color(0xFFBBF7D0),
        c300 = Color(0xFF86EFAC), c400 = Color(0xFF4ADE80), c500 = Color(0xFF22C55E),
        c600 = Color(0xFF16A34A), c700 = Color(0xFF15803D), c800 = Color(0xFF166534),
        c900 = Color(0xFF14532D)
    )

    val Emerald = ColorFamily(
        c50 = Color(0xFFECFDF5), c100 = Color(0xFFD1FAE5), c200 = Color(0xFFA7F3D0),
        c300 = Color(0xFF6EE7B7), c400 = Color(0xFF34D399), c500 = Color(0xFF10B981),
        c600 = Color(0xFF059669), c700 = Color(0xFF047857), c800 = Color(0xFF065F46),
        c900 = Color(0xFF064E3B)
    )

    val Teal = ColorFamily(
        c50 = Color(0xFFF0FDFA), c100 = Color(0xFFCCFBF1), c200 = Color(0xFF99F6E4),
        c300 = Color(0xFF5EEAD4), c400 = Color(0xFF2DD4BF), c500 = Color(0xFF14B8A6),
        c600 = Color(0xFF0D9488), c700 = Color(0xFF0F766E), c800 = Color(0xFF115E59),
        c900 = Color(0xFF134E4A)
    )

    val Cyan = ColorFamily(
        c50 = Color(0xFFECFEFF), c100 = Color(0xFFCFFAFE), c200 = Color(0xFFA5F3FC),
        c300 = Color(0xFF67E8F9), c400 = Color(0xFF22D3EE), c500 = Color(0xFF06B6D4),
        c600 = Color(0xFF0891B2), c700 = Color(0xFF0E7490), c800 = Color(0xFF155E75),
        c900 = Color(0xFF164E63)
    )

    val Sky = ColorFamily(
        c50 = Color(0xFFF0F9FF), c100 = Color(0xFFE0F2FE), c200 = Color(0xFFBAE6FD),
        c300 = Color(0xFF7DD3FC), c400 = Color(0xFF38BDF8), c500 = Color(0xFF0EA5E9),
        c600 = Color(0xFF0284C7), c700 = Color(0xFF0369A1), c800 = Color(0xFF075985),
        c900 = Color(0xFF0C4A6E)
    )

    val Blue = ColorFamily(
        c50 = Color(0xFFEFF6FF), c100 = Color(0xFFDBEAFE), c200 = Color(0xFFBFDBFE),
        c300 = Color(0xFF93C5FD), c400 = Color(0xFF60A5FA), c500 = Color(0xFF3B82F6),
        c600 = Color(0xFF2563EB), c700 = Color(0xFF1D4ED8), c800 = Color(0xFF1E40AF),
        c900 = Color(0xFF1E3A8A)
    )

    val Indigo = ColorFamily(
        c50 = Color(0xFFEEF2FF), c100 = Color(0xFFE0E7FF), c200 = Color(0xFFC7D2FE),
        c300 = Color(0xFFA5B4FC), c400 = Color(0xFF818CF8), c500 = Color(0xFF6366F1),
        c600 = Color(0xFF4F46E5), c700 = Color(0xFF4338CA), c800 = Color(0xFF3730A3),
        c900 = Color(0xFF312E81)
    )

    val Violet = ColorFamily(
        c50 = Color(0xFFF5F3FF), c100 = Color(0xFFEDE9FE), c200 = Color(0xFFDDD6FE),
        c300 = Color(0xFFC4B5FD), c400 = Color(0xFFA78BFA), c500 = Color(0xFF8B5CF6),
        c600 = Color(0xFF7C3AED), c700 = Color(0xFF6D28D9), c800 = Color(0xFF5B21B6),
        c900 = Color(0xFF4C1D95)
    )

    val Purple = ColorFamily(
        c50 = Color(0xFFFAF5FF), c100 = Color(0xFFF3E8FF), c200 = Color(0xFFE9D5FF),
        c300 = Color(0xFFD8B4FE), c400 = Color(0xFFC084FC), c500 = Color(0xFFA855F7),
        c600 = Color(0xFF9333EA), c700 = Color(0xFF7E22CE), c800 = Color(0xFF6B21A8),
        c900 = Color(0xFF581C87)
    )

    val Fuchsia = ColorFamily(
        c50 = Color(0xFFFDF4FF), c100 = Color(0xFFFAE8FF), c200 = Color(0xFFF5D0FE),
        c300 = Color(0xFFF0ABFC), c400 = Color(0xFFE879F9), c500 = Color(0xFFD946EF),
        c600 = Color(0xFFC026D3), c700 = Color(0xFFA21CAF), c800 = Color(0xFF86198F),
        c900 = Color(0xFF701A75)
    )

    val Pink = ColorFamily(
        c50 = Color(0xFFFDF2F8), c100 = Color(0xFFFCE7F3), c200 = Color(0xFFFBCFE8),
        c300 = Color(0xFFF9A8D4), c400 = Color(0xFFF472B6), c500 = Color(0xFFEC4899),
        c600 = Color(0xFFDB2777), c700 = Color(0xFFBE185D), c800 = Color(0xFF9D174D),
        c900 = Color(0xFF831843)
    )

    val Rose = ColorFamily(
        c50 = Color(0xFFFFF1F2), c100 = Color(0xFFFFE4E6), c200 = Color(0xFFFECDD3),
        c300 = Color(0xFFFDA4AF), c400 = Color(0xFFFB7185), c500 = Color(0xFFF43F5E),
        c600 = Color(0xFFE11D48), c700 = Color(0xFFBE123C), c800 = Color(0xFF9F1239),
        c900 = Color(0xFF881337)
    )

    val Slate = ColorFamily(
        c50 = Color(0xFFF8FAFC), c100 = Color(0xFFF1F5F9), c200 = Color(0xFFE2E8F0),
        c300 = Color(0xFFCBD5E1), c400 = Color(0xFF94A3B8), c500 = Color(0xFF64748B),
        c600 = Color(0xFF475569), c700 = Color(0xFF334155), c800 = Color(0xFF1E293B),
        c900 = Color(0xFF0F172A)
    )

    val Gray = ColorFamily(
        c50 = Color(0xFFF9FAFB), c100 = Color(0xFFF3F4F6), c200 = Color(0xFFE5E7EB),
        c300 = Color(0xFFD1D5DB), c400 = Color(0xFF9CA3AF), c500 = Color(0xFF6B7280),
        c600 = Color(0xFF4B5563), c700 = Color(0xFF374151), c800 = Color(0xFF1F2937),
        c900 = Color(0xFF111827)
    )

    val Zinc = ColorFamily(
        c50 = Color(0xFFFAFAFA), c100 = Color(0xFFF4F4F5), c200 = Color(0xFFE4E4E7),
        c300 = Color(0xFFD4D4D8), c400 = Color(0xFFA1A1AA), c500 = Color(0xFF71717A),
        c600 = Color(0xFF52525B), c700 = Color(0xFF3F3F46), c800 = Color(0xFF27272A),
        c900 = Color(0xFF18181B)
    )

    val Neutral = ColorFamily(
        c50 = Color(0xFFFAFAFA), c100 = Color(0xFFF5F5F5), c200 = Color(0xFFE5E5E5),
        c300 = Color(0xFFD4D4D4), c400 = Color(0xFFA3A3A3), c500 = Color(0xFF737373),
        c600 = Color(0xFF525252), c700 = Color(0xFF404040), c800 = Color(0xFF262626),
        c900 = Color(0xFF171717)
    )

    val Stone = ColorFamily(
        c50 = Color(0xFFFAFAF9), c100 = Color(0xFFF5F5F4), c200 = Color(0xFFE7E5E4),
        c300 = Color(0xFFD6D3D1), c400 = Color(0xFFA8A29E), c500 = Color(0xFF78716C),
        c600 = Color(0xFF57534E), c700 = Color(0xFF44403C), c800 = Color(0xFF292524),
        c900 = Color(0xFF1C1917)
    )
}