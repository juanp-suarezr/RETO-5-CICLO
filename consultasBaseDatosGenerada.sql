-- Consulta 1: 
-- Generar correo electrónico de los Usuarios/Funcionarios registrados, 
-- utilizando el id para garantizar que sean diferentes.
-- La estructura del correo debe ser: 
-- 1) La primera letra del nombre en minúscula,
-- 2) seguido de un punto, 
-- 3) luego el primer apellido, 
-- 4) y finalmente el id que tenga en base de datos el usuario. 
-- Establecer como dominio: empresa.com.co

-- select Nombre, Fecha_Nacimiento from Usuario where Usuario.Estrato > 3;
-- select LOWER( SUBSTR(Nombre,1,1)) || '.' || LOWER( SUBSTR(Primer_Apellido,1,4))    || ID_Usuario || '@abc.com.co' as email_empresa from Usuario order by ID_Usuario DESC;
select LOWER( SUBSTR(Nombre,1,1)) || '.' || LOWER( Primer_Apellido )    || ID_Usuario || '@empresa.com.co' as email_empresa from Usuario order by length(email_empresa) DESC;

-- Resultado1:
email_empresa               
----------------------------
j.rodriguez20@empresa.com.co
j.rodriguez6@empresa.com.co 
h.granada12@empresa.com.co  
l.escobar19@empresa.com.co  
b.escobar22@empresa.com.co  
b.escobar23@empresa.com.co  
b.granada24@empresa.com.co  
l.jimenez25@empresa.com.co  
m.escobar29@empresa.com.co  
l.escobar30@empresa.com.co  
c.granada3@empresa.com.co   
d.arteaga4@empresa.com.co   
g.orozco10@empresa.com.co   
c.ocampo11@empresa.com.co   
g.orozco13@empresa.com.co   
b.orozco14@empresa.com.co   
d.orozco17@empresa.com.co   
j.falcon26@empresa.com.co   
l.falcon1@empresa.com.co    
d.orozco5@empresa.com.co    
j.pelaez8@empresa.com.co    
l.falcon9@empresa.com.co    
b.lopez15@empresa.com.co    
j.lopez16@empresa.com.co    
h.perez18@empresa.com.co    
c.perez21@empresa.com.co    
b.gomez27@empresa.com.co    
l.perez28@empresa.com.co    
b.lopez2@empresa.com.co     
p.perez7@empresa.com.co
-- **************************************************************

-- Consulta 2:
-- Edad promedio redondeada de los usuarios, aproximando la edad 
-- de estos como los años transcurridos desde su fecha de 
-- nacimiento hasta la actual.

select round(avg(datetime('now') - Usuario.Fecha_Nacimiento)) as edad_promedio
from Usuario;

-- select  ROUND((julianday(datetime('now')) - julianday(Usuario.Fecha_Nacimiento))/365.25)  as anios,
--         Usuario.Fecha_Nacimiento,
--         datetime('now')
-- from Usuario; --limit 5;

-- select  datetime('now') - Usuario.Fecha_Nacimiento  as anios,
--         Usuario.Fecha_Nacimiento,
--         datetime('now')
-- from Usuario order by anios desc limit 5;

-- Resultado2:
edad_promedio
-------------
50.0
-- **************************************************************
-- Consulta 3:
-- Serial y Clasificación -> Dinero invertido en compra
--    de materiales en cada proyecto

-- select  Proyecto.ID_Proyecto, 
--         Proyecto.Serial, 
--         Proyecto.Clasificacion 
-- from Proyecto
-- order by Proyecto.Clasificacion 
-- limit 100;

-- select * from Proyecto_Material_NN limit 20;

-- select count

-- select  pm.ID_Proyecto,
--         sum(pm.Cantidad * m.Precio_Unidad)
-- from Proyecto_Material_NN pm
-- inner join MaterialConstruccion m
-- on pm.ID_MaterialConstruccion = m.ID_MaterialConstruccion;
-- group by pm.ID_Proyecto;

-- select  pm.ID_Proyecto,
--         pm.Cantidad,
--         pm.ID_MaterialConstruccion, 
--         m.Precio_Unidad
-- from Proyecto_Material_NN pm
-- inner join MaterialConstruccion m
-- on pm.ID_MaterialConstruccion = m.ID_MaterialConstruccion
-- order by pm.ID_Proyecto;

-- select count(*) from Proyecto;

select  c.ID_Proyecto,
        p.Clasificacion,
        sum(c.Cantidad * m.Precio_Unidad) as Gasto_Compras,
        p.Serial     
from Compra c
join MaterialConstruccion m
on c.ID_MaterialConstruccion = m.ID_MaterialConstruccion
join Proyecto p
on p.ID_Proyecto = c.ID_Proyecto
group by c.ID_Proyecto
order by Gasto_Compras desc
limit 10;

-- Resultado3:
ID_Proyecto  Clasificacion  inversion_materiales_proyecto  Serial    
-----------  -------------  -----------------------------  ----------
27           Apartamento    127892                         Ithk093FI 
93           Condominio     125821                         5uzw3t3BP 
78           Casa           122112                         rosm0AsBP 
50           Condominio     111554                         8lHyBRCBP 
17           Apartaestudio  110010                         ON5FVd4BP
-- **************************************************************
-- Consulta 4:
-- Líderes (ID, nombre, primer apellido y clasificación) 
-- de proyectos que no son ni clasificación 2 ni clasificación 5 
-- ordenados alfabéticamente.

-- select Proyecto.Clasificacion from Proyecto;
select  Lider.ID_Lider, 
        Lider.Nombre, 
        Lider.Primer_Apellido, 
        Lider.Clasificacion 
from Lider 
where Lider.Clasificacion not in (2,5)
order by Lider.Clasificacion desc, Lider.ID_Lider asc;

-- select Lider.Nombre, Lider.Clasificacion 
-- from Lider 
-- LIMIT 15;

-- Resultado4:

-- **************************************************************
-- Consulta 5:
-- Bancos ordenados de mayor a menor según el área de construcción 
-- promedio de los proyectos que respaldan.

-- Resultado5:
-- select distinct Banco_Vinculado from Proyecto;
select Proyecto.Banco_Vinculado from Proyecto;
select * from Lider;

select p.Banco_Vinculado, AVG(tp.Area_Max) as Area_Promedio 
from Proyecto p
inner join Tipo tp on
p.ID_Tipo = tp.ID_Tipo
group by p.Banco_Vinculado
order by Area_Promedio desc;

-- Consulta 5b:
-- Bancos ordenados de mayor a menor según el número 
-- proyectos que financian.

select p.Banco_Vinculado, count(*) Numero_Proyectos
from Proyecto p
join Tipo tp on
p.ID_Tipo = tp.ID_Tipo
group by p.Banco_Vinculado
order by Numero_Proyectos desc;

-- Verificación
select sum(Numero_Proyectos) as Total_Proyectos from (

    select p.Banco_Vinculado, count(*) Numero_Proyectos
    from Proyecto p
    join Tipo tp on
    p.ID_Tipo = tp.ID_Tipo
    group by p.Banco_Vinculado
    order by Numero_Proyectos desc

);


-- **************************************************************

-- Otras:
-- a) Ranking de las constructoras según el número de proyectos que 
--    tienen a cargo.

select  Proyecto.Constructora, 
        COUNT(*) as Numero_Proyectos
from Proyecto
GROUP by Proyecto.Constructora
order by Numero_Proyectos desc;

-- b) Ranking de los líderes (ID, nombre, primer apellido) 
--    según el número de proyectos que tiene a cargo.

-- select  l.ID_Lider, 
--         l.Nombre, 
--         l.Primer_Apellido
-- from Lider l
-- join Proyecto p ON
-- l.ID_Lider = p.ID_Lider;

select  l.ID_Lider, 
        l.Nombre, 
        l.Primer_Apellido,
        COUNT(*) as Numero_Proyectos
from Lider l
join Proyecto p ON
l.ID_Lider = p.ID_Lider
GROUP by p.ID_Lider
order by Numero_Proyectos desc;

-- c) Los dos cargos que lideran menos proyectos.

-- select l.Cargo, COUNT(*) as Numero_Proyectos
-- from Lider l
-- join Proyecto P ON
-- l.ID_Lider = p.ID_Lider
-- group by l.Cargo
-- order by Numero_Proyectos asc;

select l.Cargo, COUNT(*) as Numero_Proyectos
from Lider l
join Proyecto P ON
l.ID_Lider = p.ID_Lider
group by l.Cargo
order by Numero_Proyectos asc
limit 2;

-- d) Los 5 Proyectos (ID_Proyecto, Clasificacion, Area_Max) 
--    donde más pedidos(compras) se hicieron de Granito.

select  p.ID_Proyecto,
        p.Clasificacion,
        t.Area_Max,
        count(*) No_Compras_Granito
from Proyecto P
join Tipo t ON
p.ID_Tipo = t.ID_Tipo
join Compra c ON
p.ID_Proyecto = c.ID_Proyecto
join MaterialConstruccion m ON
c.ID_MaterialConstruccion = m.ID_MaterialConstruccion
WHERE m.Nombre_Material = 'Granito'
GROUP BY p.ID_Proyecto
ORDER BY No_Compras_Granito DESC,
         p.ID_Proyecto ASC
LIMIT 5;

select  p.ID_Proyecto,
        p.Clasificacion,
        t.Area_Max,
        count(*) No_Compras_Granito
from Proyecto P
join Tipo t ON
p.ID_Tipo = t.ID_Tipo
join Compra c ON
p.ID_Proyecto = c.ID_Proyecto
join MaterialConstruccion m ON
c.ID_MaterialConstruccion = m.ID_MaterialConstruccion
WHERE m.Nombre_Material = 'Granito'
GROUP BY p.ID_Proyecto
ORDER BY No_Compras_Granito DESC
LIMIT 5;

-- Revisar las compras de un proyecto (Cantidad de Cada Material)
select  c.ID_Proyecto,
        c.Cantidad,
        m.Nombre_Material        
from Compra c
join MaterialConstruccion m on
c.ID_MaterialConstruccion = m.ID_MaterialConstruccion
Where c.ID_Proyecto = 18;

-- e) Los materiales importados más comprados/utilizados en los
--    proyectos, mostrando cuántas compras se han hecho
--    de cada uno. Desempatar alfabéticameente.

-- select m.Nombre_Material,
--        m.Importado,
--        COUNT(*) No_Compras
-- from MaterialConstruccion m
-- join Compra c on
-- m.ID_MaterialConstruccion = c.ID_MaterialConstruccion
-- where m.Importado = 'Si'
-- group by c.ID_MaterialConstruccion
-- order by No_Compras desc;

select m.Nombre_Material,
       m.Importado,
       COUNT(*) No_Compras
from MaterialConstruccion m
join Compra c on
m.ID_MaterialConstruccion = c.ID_MaterialConstruccion
where m.Importado = 'Si'
group by c.ID_MaterialConstruccion
order by         
        No_Compras desc,
        Nombre_Material asc;

-- eb) Los materiales nacionales más comprados/utilizados en los
--    proyectos, mostrando cuántas compras se han hecho
--    de cada uno. Desempatar alfabéticameente.
select m.Nombre_Material,
       m.Importado,
       COUNT(*) No_Compras
from MaterialConstruccion m
join Compra c on
m.ID_MaterialConstruccion = c.ID_MaterialConstruccion
where m.Importado = 'No'
group by c.ID_MaterialConstruccion
order by         
        No_Compras desc,
        Nombre_Material asc;


-- f) Ranking de los tres meses del año donde más 
--    compras se realizan.

select  Compra.Fecha,
        strftime("%m-%Y", Compra.Fecha) as 'month-year',
        COUNT(*) Numero_Compras
from Compra
group by strftime("%m-%Y", Compra.Fecha); 

select  strftime("%m", Compra.Fecha) as Mes,
        COUNT(*) Numero_Compras
from Compra
group by Mes
order by Numero_Compras desc
limit 3;

select  strftime("%m", Compra.Fecha) as Mes,
        COUNT(*) Numero_Compras
from Compra
group by Mes
order by Numero_Compras desc;

-- Consultas para validación de resultado
select sum(Numero_Compras) from (
    select  strftime("%m-%Y", Compra.Fecha) as Mes,
            COUNT(*) Numero_Compras
    from Compra
    group by Mes
);
select count(*) from Compra;


-- select SUM(transaction) as Price, 
--        strftime("%m-%Y", transDate) as 'month-year' 
--        from transaction group by strftime("%m-%Y", transDate);


-- g) Establecer ciudad para segunda oficina/sede según 
-- la ciudad de residencia más frecuente de los líderes por 
-- fuera de Bogotá, donde está la sede principal.

select  Lider.Ciudad_Residencia,
        COUNT(*) as Numero_Lideres
from Lider
where Ciudad_Residencia NOT IN ('Bogota')
group by Lider.Ciudad_Residencia
order by Numero_Lideres DESC
limit 1;

-- select  Lider.Ciudad_Residencia,
--         COUNT(*) as Numero_Lideres
-- from Lider
-- group by Lider.Ciudad_Residencia
-- order by Numero_Lideres DESC;

