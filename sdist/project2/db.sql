--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: appointment; Type: TABLE; Schema: public; Owner: user1
--

CREATE TABLE public.appointment (
    id bigint NOT NULL,
    age integer,
    name character varying(255),
    date character varying(255),
    centre_name character varying(255),
    email character varying(255)
);


ALTER TABLE public.appointment OWNER TO user1;

--
-- Name: centre; Type: TABLE; Schema: public; Owner: user1
--

CREATE TABLE public.centre (
    date character varying(255) NOT NULL,
    centre_name character varying(255) NOT NULL,
    availability boolean,
    total_vaccines integer
);


ALTER TABLE public.centre OWNER TO user1;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: user1
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO user1;

--
-- Name: notification; Type: TABLE; Schema: public; Owner: user1
--

CREATE TABLE public.notification (
    id bigint NOT NULL,
    message character varying(255),
    email character varying(255)
);


ALTER TABLE public.notification OWNER TO user1;

--
-- Name: user1; Type: TABLE; Schema: public; Owner: user1
--

CREATE TABLE public.user1 (
    email character varying(255) NOT NULL,
    password character varying(255)
);


ALTER TABLE public.user1 OWNER TO user1;

--
-- Name: vaccinated; Type: TABLE; Schema: public; Owner: user1
--

CREATE TABLE public.vaccinated (
    id bigint NOT NULL,
    date character varying(255),
    email character varying(255),
    vaccine character varying(255)
);


ALTER TABLE public.vaccinated OWNER TO user1;

--
-- Name: vaccine; Type: TABLE; Schema: public; Owner: user1
--

CREATE TABLE public.vaccine (
    name character varying(255) NOT NULL
);


ALTER TABLE public.vaccine OWNER TO user1;

--
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: user1
--

COPY public.appointment (id, age, name, date, centre_name, email) FROM stdin;
1	20	Rui	10-07-2021	CV Évora	l42720@alunos.uevora.pt
2	35	Manuel	10-07-2021	CV Évora	manuel@alunos.uevora.pt
3	45	António	10-07-2021	CV Évora	antonio@alunos.uevora.pt
4	19	Tomás	10-07-2021	CV Lisboa	l42784@alunos.uevora.pt
5	50	José	10-07-2021	CV Lisboa	jose@alunos.uevora.pt
\.


--
-- Data for Name: centre; Type: TABLE DATA; Schema: public; Owner: user1
--

COPY public.centre (date, centre_name, availability, total_vaccines) FROM stdin;
10-07-2021	CV Évora	t	3
10-07-2021	CV Lisboa	t	2
\.


--
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: user1
--

COPY public.notification (id, message, email) FROM stdin;
\.


--
-- Data for Name: user1; Type: TABLE DATA; Schema: public; Owner: user1
--

COPY public.user1 (email, password) FROM stdin;
l42720@alunos.uevora.pt	pass
manuel@alunos.uevora.pt	pass
antonio@alunos.uevora.pt	pass
l42784@alunos.uevora.pt	pass
jose@alunos.uevora.pt	pass
\.


--
-- Data for Name: vaccinated; Type: TABLE DATA; Schema: public; Owner: user1
--

COPY public.vaccinated (id, date, email, vaccine) FROM stdin;
\.


--
-- Data for Name: vaccine; Type: TABLE DATA; Schema: public; Owner: user1
--

COPY public.vaccine (name) FROM stdin;
Pfizer
Moderna
Astrazeneca
Johnson
Sputnik
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: user1
--

SELECT pg_catalog.setval('public.hibernate_sequence', 5, true);


--
-- Name: appointment appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);


--
-- Name: centre centre_pkey; Type: CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.centre
    ADD CONSTRAINT centre_pkey PRIMARY KEY (date, centre_name);


--
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- Name: user1 user1_pkey; Type: CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.user1
    ADD CONSTRAINT user1_pkey PRIMARY KEY (email);


--
-- Name: vaccinated vaccinated_pkey; Type: CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.vaccinated
    ADD CONSTRAINT vaccinated_pkey PRIMARY KEY (id);


--
-- Name: vaccine vaccine_pkey; Type: CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (name);


--
-- Name: notification fk4mr96fx8brg4dj7c5ap13miax; Type: FK CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fk4mr96fx8brg4dj7c5ap13miax FOREIGN KEY (email) REFERENCES public.user1(email);


--
-- Name: appointment fke99jsaehvq21e954h4vlbhhd3; Type: FK CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fke99jsaehvq21e954h4vlbhhd3 FOREIGN KEY (email) REFERENCES public.user1(email);


--
-- Name: appointment fks9f726hvhfg99ke3k3utn85tt; Type: FK CONSTRAINT; Schema: public; Owner: user1
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fks9f726hvhfg99ke3k3utn85tt FOREIGN KEY (date, centre_name) REFERENCES public.centre(date, centre_name);


--
-- PostgreSQL database dump complete
--

