package space.crowdforce.repository

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import space.crowdforce.Tables
import space.crowdforce.tables.records.UserCodesRecord
import java.time.LocalDateTime

@Repository
@Transactional(propagation = Propagation.MANDATORY)
class UserCodesRepository(
        private val dslContext: DSLContext
) {

    fun getActiveUserCode(userId: Int, expirationCodeTimeSeconds: Long): String? =
            dslContext.select(Tables.USER_CODES.CODE)
                    .where(Tables.USER_CODES.USER_ID.eq(userId))
                    .and(Tables.USER_CODES.CREATION_DATE.lessOrEqual(LocalDateTime.now().plusSeconds(expirationCodeTimeSeconds)))
                    .fetchOne()
                    .value1()

    fun upsertUserCode(userId: Int, code: String): UserCodesRecord =
            dslContext.insertInto(Tables.USER_CODES)
                    .columns(Tables.USER_CODES.USER_ID, Tables.USER_CODES.CODE)
                    .values(userId, code)
                    .returning()
                    .fetchOne()
}
