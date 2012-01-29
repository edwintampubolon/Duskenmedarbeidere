package no.dusken.intern.service;

import no.dusken.common.service.GenericService;
import no.dusken.intern.intern.model.Kommentar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KommentarService extends GenericService<Kommentar> {
    List<Kommentar> findByKommentarTilOrderByTimeCreatedDesc(String galleri);
    Page<Kommentar> findByKommentarTilOrderByTimeCreatedDesc(String galleri, Pageable pageable);

    @Query("select count(k) from Kommentar k where k.kommentarTil = :kommentarTil")
    Long findCountByKommentarTil(@Param("kommentarTil") String kommentarTil);
}
