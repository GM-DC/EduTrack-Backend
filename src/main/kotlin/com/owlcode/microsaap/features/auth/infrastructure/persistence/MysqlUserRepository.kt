package com.owlcode.microsaap.features.auth.infrastructure.persistence

import com.owlcode.microsaap.features.auth.domain.model.User
import com.owlcode.microsaap.features.auth.domain.repository.UserRepository
import com.owlcode.microsaap.features.auth.infrastructure.persistence.jpa.SpringDataUserRepository
import com.owlcode.microsaap.features.auth.infrastructure.persistence.mapper.UserMapper
import org.springframework.stereotype.Repository

/**
 * Implementación del repositorio de usuario usando MySQL
 */
@Repository
class MysqlUserRepository(
    private val springDataUserRepository: SpringDataUserRepository,
    private val userMapper: UserMapper
) : UserRepository {

    override fun findById(id: Long): User? {
        return springDataUserRepository.findById(id)
            .map { userMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findByEmail(email: String): User? {
        return springDataUserRepository.findByEmail(email)
            ?.let { userMapper.toDomain(it) }
    }

    override fun existsByEmail(email: String): Boolean {
        return springDataUserRepository.existsByEmail(email)
    }

    override fun save(user: User): User {
        val entity = userMapper.toEntity(user)
        val savedEntity = springDataUserRepository.save(entity)
        return userMapper.toDomain(savedEntity)
    }

    override fun deleteById(id: Long) {
        springDataUserRepository.deleteById(id)
    }

    override fun findActiveUsers(): List<User> {
        return springDataUserRepository.findByIsActiveTrue()
            .let { userMapper.toDomainList(it) }
    }

    override fun findByNameContaining(name: String): List<User> {
        return springDataUserRepository.findByNameContaining(name)
            .let { userMapper.toDomainList(it) }
    }
}
