/* Exercise 5 */


/* a) */
select distinct taxi.Matricula
from taxi, modelo
where modelo.Marca like 'Mercedes' and modelo.Modelo = taxi.Modelo


/* b) */
select distinct motorista.NomeM
from motorista, turno
where (turno.Matricula like '19-AA-22' or turno.Matricula like '21-AA-22') and turno.Nbi = motorista.Nbi


/* c) */
select distinct telefone.Telefone, telefone.Telemovel
from telefone, pedido, servico, turno
where pedido.Nif = 600700800900 and pedido.HoraInicioP = servico.HoraInicioS 
      and (turno.DataInicioT like '02/01/2016' or turno.DataInicioT like '03/01/2016') 
      and servico.Matricula = turno.Matricula and turno.Nbi = telefone.Nbi


/* d) */
select distinct modelo.Marca, modelo.Modelo
from modelo, taxi, motorista, turno
where motorista.NomeM like 'Anibal Silva' and motorista.Nbi = turno.Nbi and turno.Matricula = taxi.Matricula 
      and taxi.Modelo = modelo.Modelo


/* e) */
select distinct motorista.NomeM
from motorista
except
select distinct motorista.NomeM
from motorista, pedido, servico, turno
where pedido.Nif = 600700800900 and pedido.HoraInicioP = servico.HoraInicioS 
      and (turno.DataInicioT like '02/01/2016' or turno.DataInicioT like '03/01/2016') 
      and servico.Matricula = turno.Matricula and turno.Nbi = motorista.Nbi


/* f) */
select distinct motorista.NomeM
from motorista
except
select motorista.NomeM
from motorista, turno
where (turno.Matricula like '19-AA-22' or turno.Matricula like '21-AA-22') and turno.Nbi = motorista.Nbi


/* g) */
select distinct motorista.NomeM
from motorista, turno
where turno.Matricula like '19-AA-22' and turno.Nbi = motorista.Nbi
intersect
select distinct motorista.NomeM
from motorista, turno
where turno.Matricula like '20-AA-22' and turno.Nbi = motorista.Nbi
intersect
select distinct motorista.NomeM
from motorista, turno
where turno.Matricula like '21-AA-22' and turno.Nbi = motorista.Nbi
intersect
select distinct motorista.NomeM
from motorista, turno
where turno.Matricula like '22-AA-22' and turno.Nbi = motorista.Nbi


/* h) */
select distinct motorista.NomeM, count(servico.Matricula) as num_servicos_total
from servico, turno, motorista
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
order by num_servicos_total desc


/* i) */
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho
from servico, turno, motorista
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
order by total_ganho desc


/* j) */
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '02/01/2016' and turno.Matricula like '19-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '02/01/2016' and turno.Matricula like '20-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '03/01/2016' and turno.Matricula like '21-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '03/01/2016' and turno.Matricula like '22-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '12/07/2016' and turno.Matricula like '19-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '23/09/2016' and turno.Matricula like '20-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '30/10/2016' and turno.Matricula like '20-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '02/11/2016' and turno.Matricula like '20-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '10/11/2016' and turno.Matricula like '22-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, sum(servico.Valor) as total_ganho_servico
from servico, turno, motorista
where turno.DataInicioT like '08/12/2016' and turno.Matricula like '22-AA-22' and turno.DataInicioT = servico.DataInicioS 
      and turno.Matricula = servico.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
order by total_ganho_servico desc


/* k) */
select distinct taxi.Matricula, modelo.Marca, modelo.Modelo, max(turno.KmFim - turno.KmInicio) as km_percorridos_turno
from taxi, modelo, turno
where turno.Matricula = taxi.Matricula and taxi.Modelo = modelo.Modelo
group by taxi.Matricula, modelo.Marca, modelo.Modelo
order by km_percorridos_turno desc


/* l) */
select avg(pedido.TempoEspera) as Tempo_Medio_Espera
from pedido


/* m) */
select distinct cliente1.NomeC, count(*) as num_pedidos from pedido, cliente1 where pedido.Nif = cliente1.Nif
group by cliente1.NomeC
having count (*) = (select max(num_pedidos) from (select pedido.Nif, count(*) as num_pedidos from pedido
group by pedido.Nif) pedido)


/* n) */
select distinct taxi.Matricula, modelo.Marca, modelo.Modelo, ((sum(servico.Valor) / sum(servico.Kms)) - modelo.Consumo*0.01) as mais_lucrativo
from taxi, modelo, servico
where servico.Matricula like '19-AA-22' and servico.Matricula = taxi.Matricula and taxi.Modelo = modelo.Modelo
group by taxi.Matricula, modelo.Marca, modelo.Modelo
union
select distinct taxi.Matricula, modelo.Marca, modelo.Modelo, ((sum(servico.Valor) / sum(servico.Kms)) - modelo.Consumo*0.01) as mais_lucrativo
from taxi, modelo, servico
where servico.Matricula like '20-AA-22' and servico.Matricula = taxi.Matricula and taxi.Modelo = modelo.Modelo
group by taxi.Matricula, modelo.Marca, modelo.Modelo
union
select distinct taxi.Matricula, modelo.Marca, modelo.Modelo, ((sum(servico.Valor) / sum(servico.Kms)) - modelo.Consumo*0.01) as mais_lucrativo
from taxi, modelo, servico
where servico.Matricula like '21-AA-22' and servico.Matricula = taxi.Matricula and taxi.Modelo = modelo.Modelo
group by taxi.Matricula, modelo.Marca, modelo.Modelo
union
select distinct taxi.Matricula, modelo.Marca, modelo.Modelo, ((sum(servico.Valor) / sum(servico.Kms)) - modelo.Consumo*0.01) as mais_lucrativo
from taxi, modelo, servico
where servico.Matricula like '22-AA-22' and servico.Matricula = taxi.Matricula and taxi.Modelo = modelo.Modelo
group by taxi.Matricula, modelo.Marca, modelo.Modelo
order by mais_lucrativo desc


/* o) */
select distinct motorista.NomeM, (sum(turno.KmFim - turno.KmInicio) - sum(servico.Kms)) as km_percorridos 
from turno, servico, motorista
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = 1234 
      and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, (sum(turno.KmFim - turno.KmInicio) - sum(servico.Kms)) as km_percorridos 
from turno, servico, motorista
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = 1235 
      and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, (sum(turno.KmFim - turno.KmInicio) - sum(servico.Kms)) as km_percorridos 
from turno, servico, motorista
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = 1236 
      and turno.Nbi = motorista.Nbi
group by motorista.NomeM
union
select distinct motorista.NomeM, (sum(turno.KmFim - turno.KmInicio) - sum(servico.Kms)) as km_percorridos 
from turno, servico, motorista
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = 1237 
      and turno.Nbi = motorista.Nbi
group by motorista.NomeM
order by km_percorridos desc


/* p) */
select distinct motorista.NomeM, max((servico.Kms*1000) / (servico.TempoServico*60)) as velocidade_servico_mais_rapido
from motorista, servico, turno 
where servico.DataInicioS = turno.DataInicioT and servico.Matricula = turno.Matricula and turno.Nbi = motorista.Nbi
group by motorista.NomeM
order by velocidade_servico_mais_rapido desc


