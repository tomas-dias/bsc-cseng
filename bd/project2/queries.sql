/* Exercise 10 */


/* a) */
select distinct filme.Titulo_O
from filme, genero1
where genero1.Genero like 'Thriler' and genero1.URL = filme.URL
intersect
select distinct filme.Titulo_O
from filme, genero1
where genero1.Genero like 'Misterio' and genero1.URL = filme.URL


/* b) */ 
select distinct cliente1.NomeC
from cliente1, pagamento
where pagamento.DiaP = 2 and pagamento.MesP like 'dezembro' and pagamento.AnoP = 2019 and pagamento.Login = cliente1.Login


/* c) */
select distinct filme.Titulo_O
from filme, ator, ator_filme
where filme.Lingua_O like 'Inglês' and ator.NacionalidadeAt like 'Francesa' and
ator_filme.URL = filme.URL and ator_filme.NomeAt = ator.NomeAt


/* d) */
select sum(pagamento.Valor) as valor_total_cobrado
from pagamento
where pagamento.MesP like 'novembro'


/* e) */
select distinct filme.Titulo_O
from filme, historial1
where historial1.DataH like '02/12/19' and historial1.URL = filme.URL


/* f) */
select distinct filme.Titulo_O
from filme, cliente1, historial1
where cliente1.Login like 'joseasantos365' and historial1.Login = cliente1.Login
and historial1.URL = filme.URL


/* g) */
select distinct diretor.NomeD
from diretor
except
select distinct diretor.NomeD
from diretor, filme, diretor_filme, genero1
where genero1.Genero like 'Fantasia' and genero1.URL = filme.URL and
diretor_filme.URL = filme.URL and diretor_filme.NomeD = diretor.NomeD


/* h) */
select distinct premio_filme.URL, count(*) as num_premios from premio_filme
group by premio_filme.URL
having count (*) = (select max(num_premios) from (select premio_filme.URL, count(*) as num_premios from premio_filme
group by premio_filme.URL) premio_filme)


/* i) */
select filme.Titulo_O, count(distinct premio_ator.NomeAt) as num_premiados 
from premio_ator, ator_filme, filme 
where premio_ator.NomeAt = ator_filme.NomeAt and ator_filme.URL like 'https://imdb.to/369yR40' and ator_filme.URL = filme.URL
group by filme.Titulo_O
union
select filme.Titulo_O, count(distinct premio_ator.NomeAt) as num_premiados 
from premio_ator, ator_filme, filme 
where premio_ator.NomeAt = ator_filme.NomeAt and ator_filme.URL like 'https://imdb.to/2quN38E' and ator_filme.URL = filme.URL
group by filme.Titulo_O
union
select filme.Titulo_O, count(distinct premio_ator.NomeAt) as num_premiados 
from premio_ator, ator_filme, filme 
where premio_ator.NomeAt = ator_filme.NomeAt and ator_filme.URL like 'https://imdb.to/38rh7TY' and ator_filme.URL = filme.URL
group by filme.Titulo_O
union
select filme.Titulo_O, count(distinct premio_ator.NomeAt) as num_premiados 
from premio_ator, ator_filme, filme 
where premio_ator.NomeAt = ator_filme.NomeAt and ator_filme.URL like 'https://imdb.to/2LA6rs6' and ator_filme.URL = filme.URL
group by filme.Titulo_O
union
select filme.Titulo_O, count(distinct premio_ator.NomeAt) as num_premiados 
from premio_ator, ator_filme, filme 
where premio_ator.NomeAt = ator_filme.NomeAt and ator_filme.URL like 'https://imdb.to/2YsrxOp' and ator_filme.URL = filme.URL
group by filme.Titulo_O
order by num_premiados desc


/* j) */
select distinct historial1.Login
from historial1
where not exists (select filme.URL
                  from filme
                  where filme.URL = historial1.URL
                  except
                  select filme.URL
                  from filme
                  where filme.URL like 'https://imdb.to/2quN38E' and filme.URL = historial1.URL
                  union
                  select filme.URL
                  from filme
                  where filme.URL like 'https://imdb.to/369yR40' and filme.URL = historial1.URL)



