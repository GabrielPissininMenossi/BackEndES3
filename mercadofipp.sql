--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-10-05 16:32:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4971 (class 1262 OID 16384)
-- Name: mercado_fipp; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE mercado_fipp WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE mercado_fipp OWNER TO postgres;

\connect mercado_fipp

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16385)
-- Name: anuncio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.anuncio (
                                anu_id integer NOT NULL,
                                anu_title character varying(80),
                                anu_date date,
                                anu_desc text,
                                anu_price numeric(10,1),
                                cat_id integer,
                                usr_id integer,
                                anu_est integer,
                                anu_peso numeric(10,1)
);


ALTER TABLE public.anuncio OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16390)
-- Name: anuncio_anu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.anuncio_anu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.anuncio_anu_id_seq OWNER TO postgres;

--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 218
-- Name: anuncio_anu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.anuncio_anu_id_seq OWNED BY public.anuncio.anu_id;


--
-- TOC entry 230 (class 1259 OID 32851)
-- Name: anuncio_observer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.anuncio_observer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.anuncio_observer_seq OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 32846)
-- Name: anuncio_observer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.anuncio_observer (
                                         anu_obs_id integer DEFAULT nextval('public.anuncio_observer_seq'::regclass) NOT NULL,
                                         anu_id integer NOT NULL,
                                         usr_id integer NOT NULL
);


ALTER TABLE public.anuncio_observer OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16391)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
                                  cat_id integer NOT NULL,
                                  cat_name character varying(20)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16394)
-- Name: categoria_cat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_cat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_cat_id_seq OWNER TO postgres;

--
-- TOC entry 4974 (class 0 OID 0)
-- Dependencies: 220
-- Name: categoria_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;


--
-- TOC entry 221 (class 1259 OID 16395)
-- Name: foto_anuncio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.foto_anuncio (
                                     fot_id integer NOT NULL,
                                     fot_file bytea,
                                     fot_ext character varying(10),
                                     anu_id integer
);


ALTER TABLE public.foto_anuncio OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16400)
-- Name: foto_anuncio_fot_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.foto_anuncio_fot_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.foto_anuncio_fot_id_seq OWNER TO postgres;

--
-- TOC entry 4975 (class 0 OID 0)
-- Dependencies: 222
-- Name: foto_anuncio_fot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.foto_anuncio_fot_id_seq OWNED BY public.foto_anuncio.fot_id;


--
-- TOC entry 232 (class 1259 OID 32853)
-- Name: item_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_seq OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 32836)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
                             it_id integer DEFAULT nextval('public.item_seq'::regclass) NOT NULL,
                             it_qtde integer,
                             anu_id integer,
                             ven_id integer
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16401)
-- Name: pergunta_anuncio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pergunta_anuncio (
                                         per_id integer NOT NULL,
                                         per_text text,
                                         anu_id integer,
                                         per_resp text
);


ALTER TABLE public.pergunta_anuncio OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16406)
-- Name: pergunta_anuncio_per_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pergunta_anuncio_per_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pergunta_anuncio_per_id_seq OWNER TO postgres;

--
-- TOC entry 4976 (class 0 OID 0)
-- Dependencies: 224
-- Name: pergunta_anuncio_per_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pergunta_anuncio_per_id_seq OWNED BY public.pergunta_anuncio.per_id;


--
-- TOC entry 225 (class 1259 OID 16407)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
                                usr_id integer NOT NULL,
                                usr_name character varying(20),
                                usr_pass character varying(10),
                                usr_level character varying(1)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16410)
-- Name: usuario_usr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_usr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_usr_id_seq OWNER TO postgres;

--
-- TOC entry 4977 (class 0 OID 0)
-- Dependencies: 226
-- Name: usuario_usr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_usr_id_seq OWNED BY public.usuario.usr_id;


--
-- TOC entry 231 (class 1259 OID 32852)
-- Name: venda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.venda_seq OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 32831)
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda (
                              ven_id integer DEFAULT nextval('public.venda_seq'::regclass) NOT NULL,
                              usr_id integer
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- TOC entry 4777 (class 2604 OID 16411)
-- Name: anuncio anu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anuncio ALTER COLUMN anu_id SET DEFAULT nextval('public.anuncio_anu_id_seq'::regclass);


--
-- TOC entry 4778 (class 2604 OID 16412)
-- Name: categoria cat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN cat_id SET DEFAULT nextval('public.categoria_cat_id_seq'::regclass);


--
-- TOC entry 4779 (class 2604 OID 16413)
-- Name: foto_anuncio fot_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foto_anuncio ALTER COLUMN fot_id SET DEFAULT nextval('public.foto_anuncio_fot_id_seq'::regclass);


--
-- TOC entry 4780 (class 2604 OID 16414)
-- Name: pergunta_anuncio per_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pergunta_anuncio ALTER COLUMN per_id SET DEFAULT nextval('public.pergunta_anuncio_per_id_seq'::regclass);


--
-- TOC entry 4781 (class 2604 OID 16415)
-- Name: usuario usr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN usr_id SET DEFAULT nextval('public.usuario_usr_id_seq'::regclass);


--
-- TOC entry 4950 (class 0 OID 16385)
-- Dependencies: 217
-- Data for Name: anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.anuncio VALUES (2, 'teclado gamer', '2025-06-20', 'usado, porem funcionando', 2000.0, 1, 1, 32, 5.0);


--
-- TOC entry 4962 (class 0 OID 32846)
-- Dependencies: 229
-- Data for Name: anuncio_observer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.anuncio_observer VALUES (1, 2, 1);
INSERT INTO public.anuncio_observer VALUES (2, 2, 2);


--
-- TOC entry 4952 (class 0 OID 16391)
-- Dependencies: 219
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria VALUES (1, 'informática');
INSERT INTO public.categoria VALUES (2, 'livros');
INSERT INTO public.categoria VALUES (4, 'acessorios');
INSERT INTO public.categoria VALUES (3, 'celulares');


--
-- TOC entry 4954 (class 0 OID 16395)
-- Dependencies: 221
-- Data for Name: foto_anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.foto_anuncio VALUES (1, '\x5249464662150000574542505650382056150000f053009d012ab80090003e55248e44a3a22115e99ed0380544b38c4f93d63a6c4e2d127c08062f47bfe7f776f980f3b0f49fa601d355fe47262253dc52fd0783be417defeec7afee48ed27ebe3ed7fc0fa47df7fc1cff67fba7b047e4dfd0bcf021b7a69e817ef97dbbf627d5afe77cd8fb03ec03facfff53fb57b79ff3bc3eff03ff67d807fa07f86ffd5f701f279ffa7fb3f473f587b0b74a5fddff6784ed735adce44d77e4eab190974259387fecd4751a419df19d9dac57b6e467a59e763c97a2980a18cc2d5bed8c605ca45f9e793f10ed2479ae0454a72c42b5d5b4c176f398c0f4bc2c68559e4c4006f3d4a1850bd9bfab35e085b9b97d818d4dc3fdccd03b29b5e205ed8a981f753ee0e1568facb0a8893866a0d1eb76dade0b483ae6b7a1f6514b1dcaf662ca848c0c46d323b031d97a704fade62df3430c57f1418fd018523e61ca4c73f80ec2115a08c9fa547480d0fdc40022edd46ac7aa1fc606e6f9f67ffde75150bd0ffa6ce54113c0476a431ce15983031e93119d5634386edb1b97c33fe2f4d6a9bb33c5d07f750ea4de4c75af1fa085bdccdbf848aeadebd315947d66f28facff82f22a40320c1387a10dde744590bc4cb0ce990abd89ebecb1dfb5a51447b094820168ae60b1c99a3800e0c1b83665809104b1689ee2ebb86bf833d5bad6080e546295d834828682c113543cae3720461bc58a3f28af984ba45dc942b217b752bddbcb3c6b88af0314228639e00510eeeb0f0aa19604ce6c16a8bf9ce7725811f645c2c7dd510d5aa675f35cfd69da6e9d6ee41ef0f8a39328bb093ebe17992e45d1ffa676cd21a5739074718d52187bf4b0fd0c284dc8bbdeef660caca857fb46bd0e039dd162e1a27b6644d30290783b5e20eac3d262d31bf1e0d7dc9b37acbb090d5cff800fd47b8aa3a8324191fd9a73913567c4ae24bf83e29092c0000fefd6d1ddb960ecc800cfc3180beb294e47fe5deaff9b8a35da5fdf98763444b9b267920a5618f2738e0ab15641bb390626d32cf847a5b8a5040e0e3ffe6635ee6ac4d403d734290aadbf8b8909702f5672acf5040611581a966d620ac1fc4ef67f79e179f653ed8a17e009fb99042d0b61b9aa9319c6cf00d447caa473719c8fdc6bd90cf5c434d525307db1ceae7d837dd4d1392e3c51580fc94169b4ee111d181e5405e2cadf02ef9af1c6f3dff39468af6a686bc635cb199c802cb894e4951827954e18acdf49c56a59e7773ea0a35d4caea9564bc4e1f008e5f55860d7f778e883573ad13b1161178df066355fbb4f5be750581e8fe5141ae3ce65a6a1362c4c46e664489e55ecb160d9163e61f011e7032284711e9333ef6787b37209ef1be909c327b0caba05d600c589eba3240f4c392f1ce87549aba2ce8fc15ea38f7caece8a3dd425cec7e8f9cac9b00796716f539d112f150618862ceb0f66ef448541506891368d856c4813061c0cbc3acf110a44387c130757cabbe05007b2540142734d07f12524d47ba771a3dad2e5a0d1ef417045b9444cbe275ea374fa840bbce190ce1e0217baa6bed8e1e3708f597b105334146771b32be34d2121376b60028b4b19539a863b9708c4391c3535aafaed61a3a4f351b98588b4fcff9ad06cc130c12b456a2465f4ccd53cef212f6dd947233c5e2c7aaaf98edfeceb47b5c5e63582665a97f94e611664fe51452e77f8051f24ae0afe0b297bcf597a430c596727cf76bb701fdef58441ecf23e3b028075925c68fe777008501016894883f9c186e6e4c3b78d0ec8288a0c86be1b671087bba81bdfc3345615d46b7b46aa66f08d28feefe2fe25133dd565ff1f2fb2ee864ba2a2423d7fe45ecb8e46c828fd0064c9cef625990df37b2cbfc5b8eb5933b6b8568cbf56a765cfd1fca5c3d0efef87bc6f55ee9a0c6860762b55f8e1806f25229b36fd0748b4b0772a774d1e5a2708a39de045d3881104ec9157cf3cbaba701179f8689d428275f2144fb755a60f924643d41a768c7375804d397babd2153d4813c35300daa0583e077090ce255ea1a9ac6d80ed2a7045664611266ef425c5ad53a402d8df9ab60f87a7e68fb76cc516e8655790b1ca32002b297ae4e2c0c7346905b18e3fc918904dab0eec15f140932266bdbadf48d7a809c212efabb1f8759767877266a3c9faa0f39dcb294ed78751e57c5724394c225941db244b3073860dd7374b890d69b2390871de742ca126c931c5294774ec6a5d33d3c471875f8bce7ae95a37afb56d861d917b53c0f96c22c933dec855468184adc498fc16c9192e4387fc44962ca7fb1462d72b5f8d07402538d64584cd6a27d091e135760285e8f08555caa24553064d4fa3257a16ff77492ae39e645356c1ee8f8a58f2c514a4996c997c14ba1f65d28dc6cb7dcb933fe02d0eae7d506b9b7e5ba3ce5d0ffcbcf6a71f6e81d0f8646503c0a109aacab472608ed2a0c33c53279790e9c5011789e659100285a3d449e0dfe84a0515e53327e7b910eea2c9411bd955bf3198524053d64241f2f4c5b7d95d3f0c5ae7423e3b56f0420cce5b01a9385807e94d8cf2411e62665a1f4b8f4cf0cc24636f9db527fd1f2703ca40f541e13ea83132cc2d8a4303be0c5328475839baa8a5d232323e543be431fa6f2366817564f9ff677cbc26c5af45114145bfff0e78d216a24e4f84e81bf9e73680bc4cedf6872a1ba1060681a578d5f3fc279fc3fc79417e39368db4438212305babd5c9ebbcd44f24a5ad8b1feeff3c761fd6893c7ad5623ef45be07e98bd76273ea641bc01058aa74773306db3da53250aa5a521639a539a1113e5b2690c68f0f18fafa5cbc40139361fa8dccc642ecc11e4c8feab538b765b17a2a4f1df0c5fccbece15102b28b1fbd9d83085f6d4c36719a1743f3446b7c78deb111a3aeabea687c7d5334793a9592bdadfc351cc2f82b1fcfbc44bfacce1db703c5384a2f66a62b03cd5fd470ca1e429d39ca7e1b29fdfbc61f71ac9f1719e5b3c3b72f8c2e41b59d5228e0f4d460041a1f81ff320c72f67bb1b4544a8b36a625cd3c08c69411e9012df6714e2250a3a75b2e117ae68bef7f6756d2bbb10e2afcd6670b53f7c464913abd9f2f63fca13b9c69595b7b498e8ed005e1327acabc138f580964a4885209aa56a69e16acc987e6b5b0c2ed898a3e00246d51656cc267d3f790b00ee72e904eda971a6615ff68fa2a5624b9f5357b4f4ff2c543c51f748999fa15c49fcdab604a295ac7d5b6492b19db757b7f6f64a2129291d89fd2bbfac0b33d8d555242114de327fd5d3c11670576d15e20e614969fcc1437f1094599297e0e58d6fe8db85532485ac960a129b4066bc959fbe7128bec652a95c274fa9f804a9d64211b7480731205f62a8efab4c89daee083b88f2a9027dcb3918929df150e23f4f41bf057e966914081ed8e66fe25efc7740211b1d1642fa84805f9205ed680383c066817efa1a9b4075075b782d6e3003263f6d9916131452e8aaf4083bceeb9d66637c48f0d84de66f363d4bfb8bd0648b04e834dc1f146a3a33ca4305cccbdb2049ed85b428ab35d0901456aebf94d94c36486b5fdf8d4f501412cdef3dca6b6c4d12fda9506e9b5a645817a8c85bfb5ed57c58fa26537b06999edf7facc9e70983d8bad0e1ab63c978c41a90a78b8d8ce69cd0f5d7e6900ec8c8198e072e3134ab3de7dda2a94fc3b36cca8f1b30a188e6fe823982e377e064d1e6c3c6fa216714a60a70f8a977c61b3de4ab9c59b63f049bd9b3c5d03f790285b06cb5b6b391a42db562a68f71cf17d5fbb977609dd574c1bcc2d219ad61832b23abb5fa72f2494ab52156697c99e0b2e02c589c12ed6c86db109237fe465bcbce5ce5b91a2a35d6e28da20d79089433c07807590671df3b0b38ab9efc9364260bf45b44394f5bf2e1f7c38907994a4f9da98a159ed688314b371acb0f53dfa86cc0fe67d4557afd5ecae1f29d62f3820ed6cd9b2ab6cba6fc542b589795f7595ff4681e39675e1a4bf212cf085409edc30d204f28eded7d49f0617f015fd155611f51c1db13747f6a05cc1aa0d55fd3b24b6c0f6425b14ba63ad9504969fe1a6a06f6348b40d5f3578a2e0a27d67986a70685fdf64bce3456d09ce26566bb68ec82e596ff1c36b4cadba7e1f88a2516b1b2f6340c1651099fd7934cb3376028233403d55ee590ec7bcf2c31b689f5c3269d87854c1072fdef8277b5615616b20bb232bf7d480960916af320f1c2d3a4758400657ed6ab15c3b0bfa19dd45b4d9836f7b4d9104579f3f6911caaf3562550ef71f2d34dca71e9e432b2143c28a807817f3aff0e4d4246751c1b17e490b176acd634d1853896084bd803cee3c0a87bbc30c7f9c0d1e81990ff6d1c9059d4a2b1a88b5764cd90c7e7eb8a7b51fd79b256c7c3f1767649bc631f9da2bd1c382f55832e35723e6ca6dd09b9546ca397d35bbc7ead010a0c9c5bbbb62d82fa03b967b41e2c6ea56046e38283f9f3636b78e079593d764aedaa026c568ce239be519861f67efb183e8bbaf1637f2770048c59efacaa2bdbb3a796a1a47ff9d9da7ef0428f13931d294ce7ae78c3dba9f8f17e431beace2ab3e7f2e9c0787f4a46ed452cb8737b50856f8357bc760a42e89b22c4d73eea08564945f09f9b6410a4f6712628a37fc9ad3c73edfbe9cb8213416a4a201dd6ebf4c614eac91b0bd2a5c11fa0a5aa66d9ce6ab25528848d40edd7841c44dd68ac221fbd52b5cb2dd80e18f1fed7fa78dcd1c02a9ab2260393a602fdbd4287973a7f4b765bb72027c4186631183da6339d123066cd0248c3fda31e4bc7de6b058a9267eb8d10569d61785e4342b200d785a92a4b02a15236753a2a464ecfae7dd04b53512f4d2c0b7a774e497d1683f30ce71b7690d3c8576af4a8928b2e31b5f68cc1d354005c8e6ccc50ba82c53fbae30de6fe185e89724733198269ce741524c363084dbc5ceba14ec4a0cfbdacc458caa62ef17bad77f8e85fdf6b9febdc3be13853859b882a13bca10fb458725ef78cab8d706ba7d2ae57fcb905b0dee77d132be46e852cbc1d5d46d5873dd326aacc6a4d04528dca44b4b6d6ad4f05fd294c922f9cc707832f78c8c19dd21ef8fc9ff21eaa044e323952e2e851083fd05c493d5ebfe6e9a65e701cb0139b391a7c4475e95cff789cbec285786a4c1605c96cc222595cc6c33836ceea726a49a07e6074a24b3e65a514d6d5f1bc77eb96a50d60aa1c9e160092a1d9f7865fa39637ae0880614fcf0255efd5fda09a90763bf49a1ddabc3279f4cedf92ff3fcb4529241872e26fd3e0d2822809ff246fd6e8946f9881c15df14740b8fe885ee3434acbca21495c14fe59d757fbf603cb1994624e3f1b24e19d5f8d81bcd8005df42dfc9d6b92df3dda9a2418a672f2f0936ad39bbb770f4e3b760e0313af5358b03ef9f7c082ca1ad9c1f04de81ff16180a39cbf6734ede34973a0a1802b2e9c3e5e709eb6e7f7814c10d3d838ec64975d395860789507269d3cacf395d965ce60db31468860386d1b8e3accffb6e240e10004c0b23f0cefbe69bf0c8d815e0ba9179e671c42b997576307386c6a2897b45552208483c4f0a396344d2f88710343100068148343884b94f69112c0acd6630c63ef18e310bcfa7136731c2f4355f9654721c42a8f609f3b282f2dbcf18232c09fd85bd541e105fb49f44bdf0f3c990014cbd1128103bfc02ba3c012648b516ba9743457c01fcfed896b029f736f2358fbc142b99e5f39d7b6831b6604470acd08cede33659dca1f3d968291ef4eaeaa07408456925c43718d00660d17583ba0140cff0d90253d012ea183bbecb6eb06a194495957d4cef97ad45e2c77361be04b6386d06d49d644bfdedaeb50365305104c1cd0decf622c910a21cc66aea7306494e2bd758fb1e392ea4dc9f04950771fcc40374dc3e623c131987e6bd3f2c3dc537c68e3fa7d50558a4b741627c2f73e36476c36e2d34f402611265521dc8cee3b62642c2f9c28bb190873fd989ed61db98fbb0ff966112c1f7f9400b18c219e37a399222dd2237307162959b2ac909b6035cd281441498b1b3cecb169600799553b6771677527bc1554bbf17cf5a697bc36e207958348f30b71fd1116b9899d8efd90d119c3bd7f6bf04e0080b2804084a471be03508388417210de24377980dfcb640bb9b9aa42ad2368a5bfcdbff8291fea20fd3922d71752869e5091f740e811f409ef3743f0192f76b3070c28b093739581a9cf3f5bb5e5eb9602074cef47ad53d72d57b3a34fc17f3547d0a0a4231c9fd2241bcdd830be1b9f92d90370ab88cb5bf3558eca3df92e9a7f1b983f8dc8031d0042ef50c3db01d1decfe857cb0e2cad920cdb2b8a08f5f0f0a9b5df5895da07935caf0bf0711cd802ed6ad3c38bb5bd08bc07a18d4f43ff5b7e086f801f44b4297a54c7f9a9e23522cd6c7aa8360e666e31460e628e11bcdc3e0c6aea4669b47be6601ee856f5b82e0ffcb04b935eac418d2215156a40e61efc7c7caaa3b526db2e7cd7d79833fae10808e59e0a988ab16d394390eda50a6c1110cd4056e692560bedfff20874bfe49619037127d733c2ba0754527f9d8d1de68c29cfc7c0af86b7a29c92d396feb14e9388e4821cd43bedb555e12b9d3affca2486415fb6d41cff0b9807aba8f27a561edba6e85250f33c1333bd9fafab32fc46e37e5de9c1d1c52ed10c216655a2241e94c7d09bd9a0bae4eaa705cc63423bc3154c60688b9ba321e68b667294bc844d95136e3baa1c811c0a6a5bf1a329cfed105f9995350e5d6277534d0c935eb7d8daadb05d34d3545584d42c2189ffe9d0c76600d81a5bac785f1085864303cabb70948e6c767f506c460bd47542d5ff68d78578e8afc0bdc8fc7205430ed1fc83ffab0a54ab74589cc83e61ca464fc4906692577b1b774e8b4868e447e557fd500c904b582bb017710475d9d7764ab34b9be19a498b905abeae3c99b924120e07ff7b6893279a7433f77b78424436ee44b07dc4306596f86ad54c97d27da6a2e949368589720d9a71ac94a658a67ea20b75564ec1f0cc502ed8ed0fe1020b8b3b7d37b84dfeca36de4a5724b59303f0e3e1b4606fbdc023e086031cd33c4eb7dec96e34839a1e9aa64a1d646cdae28ac09501f1055b42ce22cc80168ec44bded858e5d713b8dea05a163beaa1d1c4e597e16428fae125c1b15c9b01476d894d1c05891cc0e3e4920121f9f601dd83f88c2b7f9fc6a44c3c01749217c5e4eac9183dbebd065421072602a680ce78288eced7cdce00116319ce4ecf9d056c5779c17b81a91c923f73f47a44252b26a85a31618f51c648d2d5b800c5e473978784014a82bc2ebb64ddd52a1c79940ab9af15d0fa0e9593fe1685d8c15d78dcfae6ab5e2861846532727f821d5186d52e14514c01bed19d917d0cb713913ccf128675ab4a64921e22f71d60c73267ff8a5fe8f8a1d87c927c75c2e053d7a27444740d622d8fff9dc56715ce99fbfb7af7ebce91a5f44457b431da3f0048490ec42bdb2632a73538800000065798228d90792a2abaa7f04dcbff8a2b69af6a7b2161a83ed1564f72cb8e8113f2f373532e2e126f3c802fe7b68a9257e8838fdd6594fd9e61ae4045d3f4950f7cf4a65d8c7931f30b43ddfe4596481c68315b77dc921508274b1f0d3dd46ec2548af1f709f6743050a5d10c5c94c27000000', 'webp', 2);


--
-- TOC entry 4961 (class 0 OID 32836)
-- Dependencies: 228
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4956 (class 0 OID 16401)
-- Dependencies: 223
-- Data for Name: pergunta_anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4958 (class 0 OID 16407)
-- Dependencies: 225
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario VALUES (1, 'adm', 'adm123', '0');
INSERT INTO public.usuario VALUES (2, 'jao', 'jao123', '1');


--
-- TOC entry 4960 (class 0 OID 32831)
-- Dependencies: 227
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 218
-- Name: anuncio_anu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.anuncio_anu_id_seq', 4, true);


--
-- TOC entry 4979 (class 0 OID 0)
-- Dependencies: 230
-- Name: anuncio_observer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.anuncio_observer_seq', 2, true);


--
-- TOC entry 4980 (class 0 OID 0)
-- Dependencies: 220
-- Name: categoria_cat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_cat_id_seq', 4, true);


--
-- TOC entry 4981 (class 0 OID 0)
-- Dependencies: 222
-- Name: foto_anuncio_fot_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.foto_anuncio_fot_id_seq', 1, true);


--
-- TOC entry 4982 (class 0 OID 0)
-- Dependencies: 232
-- Name: item_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_seq', 1, false);


--
-- TOC entry 4983 (class 0 OID 0)
-- Dependencies: 224
-- Name: pergunta_anuncio_per_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pergunta_anuncio_per_id_seq', 2, true);


--
-- TOC entry 4984 (class 0 OID 0)
-- Dependencies: 226
-- Name: usuario_usr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_usr_id_seq', 2, true);


--
-- TOC entry 4985 (class 0 OID 0)
-- Dependencies: 231
-- Name: venda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_seq', 1, false);


--
-- TOC entry 4800 (class 2606 OID 32850)
-- Name: anuncio_observer anuncio_observer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anuncio_observer
    ADD CONSTRAINT anuncio_observer_pkey PRIMARY KEY (anu_obs_id);


--
-- TOC entry 4786 (class 2606 OID 16417)
-- Name: anuncio anuncio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anuncio
    ADD CONSTRAINT anuncio_pkey PRIMARY KEY (anu_id);


--
-- TOC entry 4788 (class 2606 OID 16419)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (cat_id);


--
-- TOC entry 4790 (class 2606 OID 16421)
-- Name: foto_anuncio foto_anuncio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foto_anuncio
    ADD CONSTRAINT foto_anuncio_pkey PRIMARY KEY (fot_id);


--
-- TOC entry 4798 (class 2606 OID 32840)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (it_id);


--
-- TOC entry 4792 (class 2606 OID 16423)
-- Name: pergunta_anuncio pergunta_anuncio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pergunta_anuncio
    ADD CONSTRAINT pergunta_anuncio_pkey PRIMARY KEY (per_id);


--
-- TOC entry 4794 (class 2606 OID 16425)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usr_id);


--
-- TOC entry 4796 (class 2606 OID 32835)
-- Name: venda venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_pkey PRIMARY KEY (ven_id);


--
-- TOC entry 4801 (class 2606 OID 16426)
-- Name: anuncio anuncio_cat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anuncio
    ADD CONSTRAINT anuncio_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES public.categoria(cat_id) NOT VALID;


--
-- TOC entry 4802 (class 2606 OID 16431)
-- Name: anuncio anuncio_usr_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anuncio
    ADD CONSTRAINT anuncio_usr_id_fkey FOREIGN KEY (usr_id) REFERENCES public.usuario(usr_id) NOT VALID;


--
-- TOC entry 4803 (class 2606 OID 16436)
-- Name: foto_anuncio foto_anuncio_anu_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foto_anuncio
    ADD CONSTRAINT foto_anuncio_anu_id_fkey FOREIGN KEY (anu_id) REFERENCES public.anuncio(anu_id);


--
-- TOC entry 4804 (class 2606 OID 16441)
-- Name: pergunta_anuncio pergunta_anuncio_anu_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pergunta_anuncio
    ADD CONSTRAINT pergunta_anuncio_anu_id_fkey FOREIGN KEY (anu_id) REFERENCES public.anuncio(anu_id);


--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-10-05 16:32:43

--
-- PostgreSQL database dump complete
--

