import React from "react";

import { Link, useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";

import {
  ActionIcon,
  Badge,
  Button,
  Card,
  Center,
  Container,
  Divider,
  Grid,
  Group,
  Image,
  Paper,
  Spoiler,
  Stack,
  Text,
  useMantineTheme,
} from "@mantine/core";
import { showNotification } from "@mantine/notifications";
import {
  IconCircleCheck,
  IconCircleX,
  IconCreditCard,
  IconEdit,
  IconShoppingCartPlus,
} from "@tabler/icons";

import * as cartApi from "../../api/cart";
import { ArticleResponse } from "../../api/types";
import ErrorAlert from "../../components/ErrorAlert";
import ImageCarousel from "../../components/ImageCarousel";
import Spinner from "../../components/Spinner";
import PriceDisplay from "../../components/PriceDisplay";
import * as actions from "./ArticleDetails.actions";
import * as selectors from "./ArticleDetails.selectors";
import * as cartActions from "../../store/shared/cart.actions";
import * as cartSelectors from "../../store/shared/cart.selectors";
import { useAppDispatch } from "../../store/store";

export default function ArticleDetails() {
  const { id } = useParams();
  const dispatch = useAppDispatch();

  const status = useSelector(selectors.status);

  React.useEffect(() => {
    dispatch(actions.findById(Number(id)));
  }, [dispatch, id]);

  if (status === "error") {
    return <ErrorAlert />;
  }

  if (status !== "success") {
    return <Spinner />;
  }

  return (
    <Container size="md">
      <ArticleDisplay />
    </Container>
  );
}

function ArticleDisplay() {
  const result = useSelector(selectors.result);

  return (
    <Stack>
      <Group position="apart">
        <Group>
          <ActionIcon component={Link} to={`/article/${result!.id}/update`}>
            <IconEdit color="yellow" />
          </ActionIcon>
          <TitleDisplay />
        </Group>
        <NameDisplay genres={result!.genres} />
      </Group>

      <ImageCarousel images={result!.pictures} />

      <Grid gutter={10}>
        <Grid.Col span={8}>
          <Stack spacing={10}>
            <DescriptionCard />
            <CategoryCard />
          </Stack>
        </Grid.Col>
        <Grid.Col span={4}>
          <Stack spacing={10}>
            {result!.alreadyBought ? <AlreadyBoughtCard /> : <BuyCard />}
            <InfoCard />
          </Stack>
        </Grid.Col>
      </Grid>
      <DlcCard />
    </Stack>
  );
}

function TitleDisplay() {
  const result = useSelector(selectors.result);
  const theme = useMantineTheme();

  return (
    <Group align="center" position="left">
      {result!.articleType === "DLC" && <Badge color="blue">DLC</Badge>}
      <Text
        size="xl"
        weight="bold"
        color={theme.colorScheme === "dark" ? "white" : "black"}
      >
        {result!.title}
      </Text>
    </Group>
  );
}

function AlreadyBoughtCard() {
  const result = useSelector(selectors.result);

  return (
    <Card>
      <Stack>
        <Center>
          <Text weight="bold" mr={5}>
            Price
          </Text>
          <PriceDisplay price={result!.price} currency={result!.currency} />
        </Center>
        <Center>
          <Text weight="bold">Already bought article!</Text>
        </Center>
      </Stack>
    </Card>
  );
}

function BuyCard() {
  const dispatch = useAppDispatch();

  const result = useSelector(selectors.result);
  const cartItems = useSelector(cartSelectors.items);

  const itemExistsInCart: boolean =
    cartItems.filter((item) => item.id === result!.id).length > 0;

  const onBuy = async () => {
    try {
      await cartApi.create({ articles: [result!.id] });

      showNotification({
        message: "Items successfully purchased",
        color: "green",
        icon: <IconCircleCheck />,
      });

      dispatch(cartActions.removeItem(result!.id));
      dispatch(actions.findById(result!.id));
    } catch (error) {
      showNotification({
        message: "Error purchasing items",
        color: "red",
        icon: <IconCircleX />,
      });
    }
  };

  const onAddToCart = () => {
    dispatch(cartActions.addItem(result!));

    showNotification({
      message: "Added to cart",
      color: "green",
      icon: <IconShoppingCartPlus />,
    });
  };

  return (
    <Card>
      <Stack>
        <Center>
          <Text weight="bold" mr={5}>
            Price
          </Text>
          <PriceDisplay price={result!.price} currency={result!.currency} />
        </Center>
        <Button fullWidth={true} onClick={onBuy} rightIcon={<IconCreditCard />}>
          Buy
        </Button>
        <Button
          fullWidth={true}
          color="orange"
          onClick={onAddToCart}
          rightIcon={<IconShoppingCartPlus />}
          disabled={itemExistsInCart}
        >
          Add to Cart
        </Button>
      </Stack>
    </Card>
  );
}

function CategoryCard() {
  const result = useSelector(selectors.result);

  return (
    <Card p={20}>
      <Text weight="bold" my={5}>
        Categories
      </Text>
      <NameDisplay genres={result!.categories} />
    </Card>
  );
}

function DescriptionCard() {
  const result = useSelector(selectors.result);

  return (
    <Card p={20}>
      <Text weight="bold">Description</Text>
      <Spoiler maxHeight={120} hideLabel="Hide" showLabel="Show more">
        <Text>{result?.description}</Text>
      </Spoiler>
    </Card>
  );
}

function DlcCard() {
  const navigate = useNavigate();
  const result = useSelector(selectors.result);

  if (result!.dlcs.length === 0) {
    return null;
  }

  return (
    <Card p={20}>
      <Text weight="bold">DLC</Text>
      <Stack align="stretch" spacing={5}>
        {result?.dlcs.map((article: ArticleResponse) => (
          <Paper
            key={article.id}
            onClick={() => navigate(`/article/${article.id}`)}
            sx={(theme) => ({
              "&:hover": {
                backgroundColor:
                  theme.colorScheme === "dark"
                    ? theme.colors.dark[5]
                    : theme.colors.gray[3],
                cursor: "pointer",
              },
            })}
          >
            <Grid>
              <Grid.Col span={4}>
                <Image src={article.pictureUrl} height={125} />
              </Grid.Col>
              <Grid.Col span={6} pl={10} pt={20}>
                <Text>{article.title}</Text>
              </Grid.Col>
              <Grid.Col span={2} pr={20} pt={20}>
                <Group position="right">
                  <Badge>
                    <PriceDisplay {...article} />
                  </Badge>
                </Group>
              </Grid.Col>
            </Grid>
          </Paper>
        ))}
      </Stack>
    </Card>
  );
}

function InfoCard() {
  const result = useSelector(selectors.result);

  return (
    <Card>
      <Stack spacing={10}>
        <Group>
          <Text size="sm" weight="bold">
            Release Date
          </Text>
          <Text size="sm">{result!.releaseDate}</Text>
        </Group>
        <Divider />
        <Group>
          <Text size="sm" weight="bold" my={0}>
            Publishers
          </Text>
          <Text size="sm">
            {result!.publishers.map((publisher) => publisher.name + " ")}
          </Text>
        </Group>
        <Divider />
        <Group>
          <Text size="sm" weight="bold">
            Developers
          </Text>
          <Text size="sm">
            {result!.developers.map((developer) => developer.name + " ")}
          </Text>
        </Group>
      </Stack>
    </Card>
  );
}

type NameDisplayProps = {
  genres: { id: number; name: string }[];
};

function NameDisplay({ genres }: NameDisplayProps) {
  return (
    <Group>
      {genres.map((genre) => (
        <Badge key={genre.id}>{genre.name}</Badge>
      ))}
    </Group>
  );
}
