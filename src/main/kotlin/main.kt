fun calculateCommission(cardType: String = "VK pay",
                        amoutPrevInMonth: Int = 0,
                        amountCurrent: Int,
                        isPromo: Boolean,
                        amountMaxMasterOnMonth:Int) = when {
    (cardType == "Mastercard" || cardType == "Maestro") ->
        if (amoutPrevInMonth + amountCurrent < amountMaxMasterOnMonth && isPromo) 0.0
        else amountCurrent * 0.006 + 2000.0
    (cardType == "Visa" || cardType == "Mir") ->
        if (amountCurrent * 0.0075 > 3_500.0) amountCurrent * 0.0075 else 3_500.0
    else -> 0.0
}

fun limitExceeding  (
    cardType: String = "VK pay",
    amountCurrent: Int,
    amountCurrentMonthOnCard: Int,
    amountCardOnDay: Int,
    amountVkPayOnMonth: Int,
    limitMaxOnDay: Int,
    limitMaxOnMounth: Int,
    limitMaxVkPayInMounth: Int,
    limitMaxVkPay: Int) =
    when {
            (amountCurrentMonthOnCard > limitMaxOnMounth ||
             amountCardOnDay + amountCurrent > limitMaxOnDay ||
             cardType == "VK pay" &&
             (amountCurrent > limitMaxVkPay || amountVkPayOnMonth > limitMaxVkPayInMounth)) -> true
        else -> false
    }

