package com.iscolt.micm.commons.base.services;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/19/2020
 * @see: com.iscolt.micm.commons.base.services
 * @version: v1.0.0
 */
public abstract class AbstractCrudService<DOMAIN, ID> implements CrudService<DOMAIN, ID> {

    private final String domainName;

    private final BaseRepository<DOMAIN, ID> repository;

    protected AbstractCrudService(BaseRepository<DOMAIN, ID> repository) {
        this.repository = repository;

        // Get domain name
        @SuppressWarnings("unchecked")
        Class<DOMAIN> domainClass = (Class<DOMAIN>) fetchType(0);
        domainName = domainClass.getSimpleName();
    }

    /**
     * Gets actual generic type.
     *
     * @param index generic type index
     * @return real generic type will be returned
     */
    private Type fetchType(int index) {
        Assert.isTrue(index >= 0 && index <= 1, "type index must be between 0 to 1");

        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[index];
    }

    /**
     * List All
     *
     * @return List
     */
    @Override
    public List<DOMAIN> listAll() {
        return repository.findAll();
    }

    /**
     * List all by sort
     *
     * @param sort sort
     * @return List
     */
    @Override
    public List<DOMAIN> listAll(Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");

        return repository.findAll(sort);
    }

    /**
     * List all by pageable
     *
     * @param pageable pageable
     * @return Page
     */
    @Override
    public Page<DOMAIN> listAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable info must not be null");

        return repository.findAll(pageable);
    }

    /**
     * List all by ids
     *
     * @param ids ids
     * @return List
     */
    @Override
    public List<DOMAIN> listAllByIds(Collection<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllById(ids);
    }

    /**
     * List all by ids and sort
     *
     * @param ids  ids
     * @param sort sort
     * @return List
     */
    @Override
    public List<DOMAIN> listAllByIds(Collection<ID> ids, Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");

        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllByIdIn(ids, sort);
    }

    /**
     * Fetch by id
     *
     * @param id id
     * @return Optional
     */
    @Override
    public Optional<DOMAIN> fetchById(ID id) {
        Assert.notNull(id, domainName + " id must not be null");

        return repository.findById(id);
    }

    /**
     * Get by id
     *
     * @param id id
     * @return DOMAIN
     */
    @Override
    public DOMAIN getById(ID id) {
//        return fetchById(id).orElseThrow(() -> new NotFoundException(domainName + " was not found or has been deleted"));
        return fetchById(id).get();
    }

    /**
     * Gets domain of nullable by id.
     *
     * @param id id
     * @return DOMAIN
     */
    @Override
    public DOMAIN getByIdOfNullable(ID id) {
        return fetchById(id).orElse(null);
    }

    /**
     * Exists by id.
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, domainName + " id must not be null");

        return repository.existsById(id);
    }

    /**
     * Must exist by id, or throw NotFoundException.
     *
     * @param id id
     */
    @Override
    public void mustExistById(ID id) {
        if (!existsById(id)) {
//            TODO throw new NotFoundException(domainName + " was not exist");
            System.out.println("TODO 这里缺个打印日志");
        }
    }

    /**
     * count all
     *
     * @return long
     */
    @Override
    public long count() {
        return repository.count();
    }

    /**
     * save by domain
     *
     * @param domain domain
     * @return DOMAIN
     */
    @Override
    public DOMAIN create(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");

        return repository.save(domain);
    }

    /**
     * save by domains
     *
     * @param domains domains
     * @return List
     */
    @Override
    public List<DOMAIN> createInBatch(Collection<DOMAIN> domains) {
        return CollectionUtils.isEmpty(domains) ? Collections.emptyList() : repository.saveAll(domains);
    }

    /**
     * Updates by domain
     *
     * @param domain domain
     * @return DOMAIN
     */
    @Override
    public DOMAIN update(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");

        return repository.saveAndFlush(domain);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    /**
     * Updates by domains
     *
     * @param domains domains
     * @return List
     */
    @Override
    public List<DOMAIN> updateInBatch(Collection<DOMAIN> domains) {
        return CollectionUtils.isEmpty(domains) ? Collections.emptyList() : repository.saveAll(domains);
    }

    /**
     * Removes by id
     *
     * @param id id
     * @return DOMAIN
     */
    @Override
    public DOMAIN removeById(ID id) {
        // Get non null domain by id
        DOMAIN domain = getById(id);

        // Remove it
        remove(domain);

        // return the deleted domain
        return domain;
    }

    /**
     * Removes by id if present.
     *
     * @param id id
     * @return DOMAIN
     */
    @Override
    public DOMAIN removeByIdOfNullable(ID id) {
        return fetchById(id).map(domain -> {
            remove(domain);
            return domain;
        }).orElse(null);
    }

    /**
     * Remove by domain
     *
     * @param domain domain
     */
    @Override
    public void remove(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");

        repository.delete(domain);
    }

    /**
     * Remove by ids
     *
     * @param ids ids
     */
    @Override
    public void removeInBatch(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        repository.deleteByIdIn(ids);
    }

    /**
     * Remove all by domains
     *
     * @param domains domains
     */
    @Override
    public void removeAll(Collection<DOMAIN> domains) {
        if (CollectionUtils.isEmpty(domains)) {
            return;
        }
        repository.deleteInBatch(domains);
    }

    /**
     * Remove all
     */
    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
