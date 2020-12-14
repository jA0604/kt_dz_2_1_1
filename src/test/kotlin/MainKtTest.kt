import org.junit.Assert.*
import org.junit.Test

class MainKtTest {
    @Test
    fun testCalculateCommissionCardTypeVisa() {
        val cardType ="Visa"
        val amoutPrevMonth = 12_0000
        val amountCurrent = 50000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = true
        val expected = 3_500.0

        val result = calculateCommission(cardType,
                                         amoutPrevMonth,
                                         amountCurrent,
                                         isPromo,
                                         amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun testCalculateCommissionCardTypeVkPay() {
        val cardType  =""
        val amoutPrevMonth = 12_0000
        val amountCurrent = 50000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = true
        val expected = 0.0

        val result = calculateCommission(cardType,
            amoutPrevMonth,
            amountCurrent,
            isPromo,
            amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun testCalculateCommissionCardTypeMaster() {
        val cardType  ="Mastercard"
        val amoutPrevInMonth = 7_602_000
        val amountCurrent = 1_500_000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = true
        val expected = 11_000.0

        val result = calculateCommission(cardType,
            amoutPrevInMonth,
            amountCurrent,
            isPromo,
            amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun testCalculateCommissionCardTypeMir() {
        val cardType  = "Mir"
        val amoutPrevInMonth = 7_602_000
        val amountCurrent = 1_500_000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = true
        val expected = 11_250.0

        val result = calculateCommission(cardType,
            amoutPrevInMonth,
            amountCurrent,
            isPromo,
            amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun testCalculateCommissionCardTypeMasterMinAmount() {
        val cardType  ="Mastercard"
        val amoutPrevInMonth = 60_000
        val amountCurrent = 10_000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = true
        val expected = 0.0

        val result = calculateCommission(cardType,
            amoutPrevInMonth,
            amountCurrent,
            isPromo,
            amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun testCalculateCommissionDefaultAmountPrevInMonth() {
        val amountCurrent = 10_000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = true
        val expected = 0.0

        val result = calculateCommission(
            amountCurrent = amountCurrent,
            isPromo = isPromo,
            amountMaxMasterOnMonth = amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }


    @Test
    fun testCalculateCommissionDefaultAmountPrevInMonthMore() {
        val cardType = "Maestro"
        val amountCurrent = 10_000_000
        val amountMaxMasterOnMonth = 7_500_000
        val isPromo = false
        val expected = 62_000.0

        val result = calculateCommission(
            cardType = cardType,
            amountCurrent = amountCurrent,
            isPromo = isPromo,
            amountMaxMasterOnMonth = amountMaxMasterOnMonth)

        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun testLimitExceeding() {
        val cardType  ="Mastercard"
        val amountCurrent = 10_000
        val amountCurrentMonthOnCard = 50_000_000
        val amountCardOnDay = 6_000
        val amountVkPayOnMonth = 7_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = false

        val result = limitExceeding(
            cardType,
            amountCurrent,
            amountCurrentMonthOnCard,
            amountCardOnDay,
            amountVkPayOnMonth,
            limitMaxOnDay,
            limitMaxOnMounth,
            limitMaxVkPayInMounth,
            limitMaxVkPay)

        assertEquals(expected, result)
    }

    @Test
    fun testLimitExceedingMoreLimitAmountDay() {
        val cardType  ="Mastercard"
        val amountCurrent = 10_000
        val amountCurrentMonthOnCard = 50_000_000
        val amountCardOnDay = 600_000_001
        val amountVkPayOnMonth = 7_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = true

        val result = limitExceeding(
            cardType,
            amountCurrent,
            amountCurrentMonthOnCard,
            amountCardOnDay,
            amountVkPayOnMonth,
            limitMaxOnDay,
            limitMaxOnMounth,
            limitMaxVkPayInMounth,
            limitMaxVkPay)

        assertEquals(expected, result)
    }


    @Test
    fun testLimitExceedingVkPay() {
        val amountCurrent = 10_000
        val amountCurrentMonthOnCard = 50_000_000
        val amountCardOnDay = 600_000_001
        val amountVkPayOnMonth = 7_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = true

        val result = limitExceeding(
            amountCurrent = amountCurrent,
            amountCurrentMonthOnCard = amountCurrentMonthOnCard,
            amountCardOnDay = amountCardOnDay,
            amountVkPayOnMonth = amountVkPayOnMonth,
            limitMaxOnDay = limitMaxOnDay,
            limitMaxOnMounth = limitMaxOnMounth,
            limitMaxVkPayInMounth = limitMaxVkPayInMounth,
            limitMaxVkPay = limitMaxVkPay)

        assertEquals(expected, result)
    }


    @Test
    fun testLimitExceedingMoreAmountOnDay() {
        val cardType = "Visa"
        val amountCurrent = 10_000_000
        val amountCurrentMonthOnCard = 70_000_000
        val amountCardOnDay = 15_000_000
        val amountVkPayOnMonth = 7_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = true

        val result = limitExceeding(
            cardType = cardType,
            amountCurrent = amountCurrent,
            amountCurrentMonthOnCard = amountCurrentMonthOnCard,
            amountCardOnDay = amountCardOnDay,
            amountVkPayOnMonth = amountVkPayOnMonth,
            limitMaxOnDay = limitMaxOnDay,
            limitMaxOnMounth = limitMaxOnMounth,
            limitMaxVkPayInMounth = limitMaxVkPayInMounth,
            limitMaxVkPay = limitMaxVkPay)

        assertEquals(expected, result)
    }

    @Test
    fun testLimitExceedingMoreAmountOnMonth() {
        val cardType = "Mir"
        val amountCurrent = 1_000_000
        val amountCurrentMonthOnCard = 7_000_000
        val amountCardOnDay = 1_000_000
        val amountVkPayOnMonth = 7_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = false

        val result = limitExceeding(
            cardType = cardType,
            amountCurrent = amountCurrent,
            amountCurrentMonthOnCard = amountCurrentMonthOnCard,
            amountCardOnDay = amountCardOnDay,
            amountVkPayOnMonth = amountVkPayOnMonth,
            limitMaxOnDay = limitMaxOnDay,
            limitMaxOnMounth = limitMaxOnMounth,
            limitMaxVkPayInMounth = limitMaxVkPayInMounth,
            limitMaxVkPay = limitMaxVkPay)

        assertEquals(expected, result)
    }

    @Test
    fun testLimitExceedingMoreAmountOnMonthonCard() {
        val amountCurrent = 12_000_000
        val amountCurrentMonthOnCard = 70_000_000
        val amountCardOnDay = 10_000_000
        val amountVkPayOnMonth = 7_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = true

        val result = limitExceeding(
            amountCurrent = amountCurrent,
            amountCurrentMonthOnCard = amountCurrentMonthOnCard,
            amountCardOnDay = amountCardOnDay,
            amountVkPayOnMonth = amountVkPayOnMonth,
            limitMaxOnDay = limitMaxOnDay,
            limitMaxOnMounth = limitMaxOnMounth,
            limitMaxVkPayInMounth = limitMaxVkPayInMounth,
            limitMaxVkPay = limitMaxVkPay)

        assertEquals(expected, result)
    }

    @Test
    fun testLimitExceedingMoreAmountCardOnDay() {
        val amountCurrent = 200_000
        val amountCurrentMonthOnCard = 7_000_000
        val amountCardOnDay = 1_000_000
        val amountVkPayOnMonth = 700_000
        val limitMaxOnDay = 15_000_000
        val limitMaxOnMounth = 60_000_000
        val limitMaxVkPayInMounth = 4_000_000
        val limitMaxVkPay = 1_500_000

        val expected = false

        val result = limitExceeding(
            amountCurrent = amountCurrent,
            amountCurrentMonthOnCard = amountCurrentMonthOnCard,
            amountCardOnDay = amountCardOnDay,
            amountVkPayOnMonth = amountVkPayOnMonth,
            limitMaxOnDay = limitMaxOnDay,
            limitMaxOnMounth = limitMaxOnMounth,
            limitMaxVkPayInMounth = limitMaxVkPayInMounth,
            limitMaxVkPay = limitMaxVkPay)

        assertEquals(expected, result)
    }

}