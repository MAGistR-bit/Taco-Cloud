package sia.tacocloud.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sia.tacocloud.data.jpa.TacoRepository;
import sia.tacocloud.domain.Taco;

import java.util.Optional;

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

    /**
     * Возвращает тако по его идентификатору
     * @param id идентификатор тако
     * @return {@link ResponseEntity<Taco>}, который возвращает объект тако вместе с HTTP-статусом.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Создает новый тако
     * @param taco тако, который нужно сохранить в базу данных
     * @return созданный тако
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }
}
