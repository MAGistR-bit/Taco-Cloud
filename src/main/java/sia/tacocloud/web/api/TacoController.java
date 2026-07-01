package sia.tacocloud.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sia.tacocloud.data.jpa.TacoRepository;
import sia.tacocloud.domain.Taco;

@RestController
@RequestMapping(path = "/api/tacos", produces = MediaType.APPLICATION_JSON_VALUE)
public class TacoController {

    private final TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    /**
     * Возвращает список недавно созданных тако.
     * <p>Результат будет отсортирован по убыванию.</p>
     * @return список недавно созданных тако
     */
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12,
                Sort.by("createdAt").descending());

        return tacoRepository.findAll(page).getContent();
    }
}
