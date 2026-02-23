package com.velas.candil.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Generic CRUD service contract.
 * This interface defines common Create, Read, Update, and Delete
 * operations for application services using Data Transfer Objects (DTOs).
 *
 * Type parameters:
 *
 * @param <C> Create DTO – represents the data required to create a new entity.
 * @param <R> Response DTO – represents the data returned to the client.
 * @param <U> Update DTO – represents the data required to update an existing entity.
 * @param <ID> Object identifier (Long, int, UUID, etc.)
 *
 * This contract is intentionally generic.
 * It does not include domain-specific queries
 *
 * Domain-specific services should extend this interface and
 * declare additional business operations as needed.
 */
public interface CrudService<C, R, U, ID> {

    /**
     * Creates a new resource.
     *
     * @param c the creation DTO containing the necessary data
     * @return the created resource as a response DTO
     */
    R create(C c);

    /**
     * Retrieves all resources using pagination.
     *
     * @param pageable pagination and sorting information
     * @return a page of response DTOs
     */
    Page<R> findAll(Pageable pageable);

    /**
     * Retrieves a single resource by its unique identifier.
     *
     * @param id the resource identifier
     * @return the resource as a response DTO
     * @throws RuntimeException if the resource does not exist
     */
    R findById(ID id);

    /**
     * Updates an existing resource.
     *
     * @param u  the update DTO containing modified data
     * @param id the identifier of the resource to update
     * @return the updated resource as a response DTO
     */
    R update(U u, ID id);

    /**
     * Deletes a resource by its unique identifier.
     *
     * @param id the resource identifier
     */
    void delete(ID id);
}